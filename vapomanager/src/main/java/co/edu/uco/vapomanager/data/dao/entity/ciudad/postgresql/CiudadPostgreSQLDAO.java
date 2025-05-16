package co.edu.uco.vapomanager.data.dao.entity.ciudad.postgresql;

import co.edu.uco.vapomanager.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.vapomanager.entity.CiudadEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private Connection conexion;

    public CiudadPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(CiudadEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<CiudadEntity> listByFilter(CiudadEntity Filter) {
        return null;
    }

    @Override
    public List<CiudadEntity> listAll() {
        return null;
    }

    @Override
    public CiudadEntity listById(UUID uuid) {
        return null;
    }

    @Override
    public void update(UUID uuid, CiudadEntity entity) {

    }
}
