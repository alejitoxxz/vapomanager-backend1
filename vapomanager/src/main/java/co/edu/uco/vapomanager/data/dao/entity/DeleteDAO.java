package co.edu.uco.vapomanager.data.dao.entity;

import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;

public interface DeleteDAO<ID> {

    void delete(ID id)throws OnlineTestException;
}
