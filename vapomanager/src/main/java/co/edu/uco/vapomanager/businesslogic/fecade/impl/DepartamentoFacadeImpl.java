package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import co.edu.uco.vapomanager.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.departamento.dto.DepartamentoDTOAssambler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.impl.DepartamentoBusinessLogicImpl;
import co.edu.uco.vapomanager.businesslogic.fecade.DepartamentoFacade;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.data.dao.factory.Factory;
import co.edu.uco.vapomanager.dto.DepartamentoDTO;

public class DepartamentoFacadeImpl implements DepartamentoFacade {

    private final DAOFactory daoFactory;
    private final DepartamentoBusinessLogic departamentoBusinessLogic;

    public DepartamentoFacadeImpl(final DataSource dataSource) throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL, dataSource);
        departamentoBusinessLogic = new DepartamentoBusinessLogicImpl(daoFactory);
    }

    @Override
    public DepartamentoDTO consultarDepartamentoPorId(final UUID id) throws VapomanagerException {
        try {
            DepartamentoDomain departamentoDomain = departamentoBusinessLogic.consultarDepartamentoPorId(id);
            DepartamentoDTO departamentoDTO = DepartamentoDTOAssambler.getInstance().toDto(departamentoDomain);
            return departamentoDTO;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la información de un departamento por ID.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar el departamento.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamentos(final DepartamentoDTO filtro) throws VapomanagerException {
        try {
            DepartamentoDomain departamentoFiltro = DepartamentoDTOAssambler.getInstance().toDomain(filtro);
            List<DepartamentoDomain> departamentos = departamentoBusinessLogic.consultarDepartamentos(departamentoFiltro);

            List<DepartamentoDTO> resultado = new ArrayList<>();
            for (DepartamentoDomain departamento : departamentos) {
                resultado.add(DepartamentoDTOAssambler.getInstance().toDto(departamento));
            }
            return resultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la lista de departamentos.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar departamentos.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
