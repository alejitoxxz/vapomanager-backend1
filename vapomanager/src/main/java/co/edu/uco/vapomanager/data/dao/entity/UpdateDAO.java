package co.edu.uco.vapomanager.data.dao.entity;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;


public interface UpdateDAO<E, ID> {

    void update(ID id, E entity)throws VapomanagerException;
}
