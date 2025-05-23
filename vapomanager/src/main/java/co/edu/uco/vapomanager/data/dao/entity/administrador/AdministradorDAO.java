package co.edu.uco.vapomanager.data.dao.entity.administrador;

import java.util.UUID;

import co.edu.uco.vapomanager.data.dao.entity.CreateDAO;
import co.edu.uco.vapomanager.data.dao.entity.DeleteDAO;
import co.edu.uco.vapomanager.data.dao.entity.ReadDAO;
import co.edu.uco.vapomanager.data.dao.entity.UpdateDAO;
import co.edu.uco.vapomanager.entity.AdministradorEntity;

public interface AdministradorDAO extends CreateDAO<AdministradorEntity>, ReadDAO<AdministradorEntity, UUID>, UpdateDAO<AdministradorEntity, UUID>, DeleteDAO<UUID> {

}
