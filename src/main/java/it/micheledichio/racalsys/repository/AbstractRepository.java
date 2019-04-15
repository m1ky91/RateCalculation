package it.micheledichio.racalsys.repository;

import java.util.List;

public interface AbstractRepository<T> {
	
	List<T> findAll();

}
