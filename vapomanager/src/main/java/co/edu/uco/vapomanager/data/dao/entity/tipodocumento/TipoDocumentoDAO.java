package co.edu.uco.vapomanager.data.dao.entity.tipodocumento;


import co.edu.uco.vapomanager.data.dao.entity.CreateDAO;
import co.edu.uco.vapomanager.data.dao.entity.ReadDAO;
import co.edu.uco.vapomanager.data.dao.entity.UpdateDAO;
import co.edu.uco.vapomanager.data.dao.entity.DeleteDAO;
import co.edu.uco.vapomanager.entity.TipoDocumentoEntity;

import java.util.UUID;
import java.util.List;


public interface TipoDocumentoDAO extends 
CreateDAO<TipoDocumentoEntity>,ReadDAO<TipoDocumentoEntity, UUID>,UpdateDAO<TipoDocumentoEntity, UUID>,DeleteDAO<UUID> {
}
