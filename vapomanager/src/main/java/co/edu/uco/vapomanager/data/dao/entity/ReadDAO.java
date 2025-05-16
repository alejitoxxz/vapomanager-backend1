package co.edu.uco.vapomanager.data.dao.entity;

import java.util.List;

public interface ReadDAO<E, ID> {

    List<E> listByFilter(E Filter);
    List<E> listAll();
    E listById(ID id);
}
