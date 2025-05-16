package co.edu.uco.vapomanager.data.dao.entity.departamento;

import co.edu.uco.vapomanager.data.dao.entity.CreateDAO;
import co.edu.uco.vapomanager.data.dao.entity.DeleteDAO;
import co.edu.uco.vapomanager.data.dao.entity.ReadDAO;
import co.edu.uco.vapomanager.data.dao.entity.UpdateDAO;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import java.util.UUID;

public interface DepartamentoDAO
        extends CreateDAO<DepartamentoEntity>, ReadDAO<DepartamentoEntity, UUID>, UpdateDAO<DepartamentoEntity, UUID>, DeleteDAO<UUID> {

}
