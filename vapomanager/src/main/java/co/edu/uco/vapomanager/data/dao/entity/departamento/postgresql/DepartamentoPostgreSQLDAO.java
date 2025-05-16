package co.edu.uco.vapomanager.data.dao.entity.departamento.postgresql;

import co.edu.uco.vapomanager.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.vapomanager.entity.DepartamentoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private Connection conexion;

    public DepartamentoPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(DepartamentoEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity Filter) {
        return null;
    }

    @Override
    public List<DepartamentoEntity> listAll() {
        return null;
    }

    @Override
    public DepartamentoEntity listById(UUID uuid) {
        return null;
    }

    @Override
    public void update(UUID uuid, DepartamentoEntity entity) {

    }
}
