package co.edu.uco.vapomanager.data.dao.entity.ciudad;

import co.edu.uco.vapomanager.data.dao.entity.CreateDAO;
import co.edu.uco.vapomanager.data.dao.entity.DeleteDAO;
import co.edu.uco.vapomanager.data.dao.entity.ReadDAO;
import co.edu.uco.vapomanager.data.dao.entity.UpdateDAO;
import co.edu.uco.vapomanager.entity.CiudadEntity;

import java.util.UUID;

public interface CiudadDAO
        extends CreateDAO<CiudadEntity>, ReadDAO<CiudadEntity, UUID>, UpdateDAO<CiudadEntity, UUID>, DeleteDAO<UUID> {

}
