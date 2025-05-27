package co.edu.uco.vapomanager.data.dao.entity.proveedor;

import java.util.UUID;

import co.edu.uco.vapomanager.data.dao.entity.CreateDAO;
import co.edu.uco.vapomanager.data.dao.entity.DeleteDAO;
import co.edu.uco.vapomanager.data.dao.entity.ReadDAO;
import co.edu.uco.vapomanager.data.dao.entity.UpdateDAO;
import co.edu.uco.vapomanager.entity.AdministradorEntity;
import co.edu.uco.vapomanager.entity.ProveedorEntity;

public interface ProveedorDAO  extends CreateDAO<ProveedorEntity>, ReadDAO<ProveedorEntity, UUID>, UpdateDAO<ProveedorEntity, UUID>, DeleteDAO<UUID> {

}
