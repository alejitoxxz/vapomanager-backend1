package co.edu.uco.vapomanager.businesslogic.fecade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import co.edu.uco.vapomanager.businesslogic.businesslogic.TipoDcumentoBusinessLogic;
import co.edu.uco.vapomanager.businesslogic.businesslogic.assembler.tipodocumento.dto.TipoDocumentoDTOAssembler;
import co.edu.uco.vapomanager.businesslogic.businesslogic.domain.TipoDocumentoDomain;
import co.edu.uco.vapomanager.businesslogic.businesslogic.impl.TipoDcumentoBusinessLogicImpl;
import co.edu.uco.vapomanager.businesslogic.fecade.TipoDocumentoFacade;
import co.edu.uco.vapomanager.crosscutting.excepciones.BusinessLogicVapomanagerException;
import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;
import co.edu.uco.vapomanager.data.dao.factory.DAOFactory;
import co.edu.uco.vapomanager.data.dao.factory.Factory;
import co.edu.uco.vapomanager.dto.TipoDocumentoDTO;

public class TipoDocumentoFacadeImpl implements TipoDocumentoFacade {

    private final DAOFactory daoFactory;
    private final TipoDcumentoBusinessLogic tipoDocumentoBusinessLogic;

    public TipoDocumentoFacadeImpl() throws VapomanagerException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRESQL);
        tipoDocumentoBusinessLogic = new TipoDcumentoBusinessLogicImpl(daoFactory);
    }

    @Override
    public TipoDocumentoDTO consultarTipoDocumentoPorId(final UUID id) throws VapomanagerException {
        try {
            TipoDocumentoDomain domain = tipoDocumentoBusinessLogic.consultarTipoDocumentoPorId(id);
            TipoDocumentoDTO dto = TipoDocumentoDTOAssembler.getInstance().toDto(domain);
            return dto;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la información de un tipo de documento por ID.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar el tipo de documento.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<TipoDocumentoDTO> consultarTipoDocumentos(final TipoDocumentoDTO filtro) throws VapomanagerException {
        try {
            TipoDocumentoDomain domainFiltro = TipoDocumentoDTOAssembler.getInstance().toDomain(filtro);
            List<TipoDocumentoDomain> domains = tipoDocumentoBusinessLogic.consultarTipoDocumentos(domainFiltro);

            List<TipoDocumentoDTO> resultado = new ArrayList<>();
            for (TipoDocumentoDomain domain : domains) {
                resultado.add(TipoDocumentoDTOAssembler.getInstance().toDto(domain));
            }
            return resultado;
        } catch (VapomanagerException excepcion) {
            throw excepcion;
        } catch (Exception exception) {
            String mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de consultar la lista de tipos de documento.";
            String mensajeUsuario = "Se produjo un problema inesperado al consultar tipos de documento.";
            throw BusinessLogicVapomanagerException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
