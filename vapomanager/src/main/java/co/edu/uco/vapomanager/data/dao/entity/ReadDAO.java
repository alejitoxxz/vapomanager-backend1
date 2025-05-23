package co.edu.uco.vapomanager.data.dao.entity;

import java.util.List;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface ReadDAO<E, ID> {

    List<E> listByFilter(E Filter)throws VapomanagerException;
    List<E> listAll()throws VapomanagerException;
    E listById(ID id)throws VapomanagerException;
}
