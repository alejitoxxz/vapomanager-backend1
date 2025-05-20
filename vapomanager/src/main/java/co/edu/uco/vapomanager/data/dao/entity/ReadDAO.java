package co.edu.uco.vapomanager.data.dao.entity;

import java.util.List;

import co.edu.uco.vapomanager.crosscutting.excepciones.OnlineTestException;

public interface ReadDAO<E, ID> {

    List<E> listByFilter(E Filter)throws OnlineTestException;
    List<E> listAll()throws OnlineTestException;
    E listById(ID id)throws OnlineTestException;
}
