package co.edu.uco.vapomanager.data.dao.entity;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

public interface CreateDAO<E> {
	
	void create(E entity)throws VapomanagerException;

}
