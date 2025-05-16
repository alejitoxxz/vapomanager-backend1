package co.edu.uco.vapomanager.data.dao.entity;

public interface UpdateDAO<E, ID> {

    void update(ID id, E entity);
}
