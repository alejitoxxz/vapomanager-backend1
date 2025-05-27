package co.edu.uco.vapomanager.businesslogic.businesslogic.assembler;

import java.util.List;

public interface DTOAssembler<T, D> {

	T toDto(D domain);
	
	D toDomain(T dto);
	
	List<D> toDomain(List<T> dtoList);
}
