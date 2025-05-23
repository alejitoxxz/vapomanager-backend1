package co.edu.uco.vapomanager.data.dao.entity;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface DeleteDAO<ID> {

    void delete(ID id)throws VapomanagerException;
}
