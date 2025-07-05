package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import co.edu.uco.vapomanager.businesslogic.businesslogic.CiudadBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.ciudad.dto.CiudadDTOAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.impl.CiudadBusinessLogicImpl;
import co.edu.uco.vapomanager.businesslogic.fecade.CiudadFacade;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.data.dao.factory.Factory;
import co.edu.uco.vapomanager.dto.CiudadDTO;

public class CiudadFacadeImpl implements CiudadFacade {

    private final DAOFactory daoFactory;
    private final CiudadBusinessLogic ciudadBusinessLogic;

    public CiudadFacadeImpl() throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL);
        ciudadBusinessLogic = new CiudadBusinessLogicImpl(daoFactory);
    }

    @Override
    public CiudadDTO consultarCiudadPorId(final UUID id) throws VapomanagerException {
        try {
            CiudadDomain ciudadDomain = ciudadBusinessLogic.consultarCiudadPorId(id);
            CiudadDTO ciudadDTO = CiudadDTOAssambler.getInstance().toDto(ciudadDomain);
            return ciudadDTO;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la información de una ciudad por ID.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar la ciudad.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<CiudadDTO> consultarCiudades(final CiudadDTO filtro) throws VapomanagerException {
        try {
            CiudadDomain ciudadFiltro = CiudadDTOAssambler.getInstance().toDomain(filtro);
            List<CiudadDomain> ciudades = ciudadBusinessLogic.consultarCiudades(ciudadFiltro);

            List<CiudadDTO> resultado = new ArrayList<>();
            for (CiudadDomain ciudad : ciudades) {
                resultado.add(CiudadDTOAssambler.getInstance().toDto(ciudad));
            }
            return resultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la lista de ciudades.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar ciudades.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
