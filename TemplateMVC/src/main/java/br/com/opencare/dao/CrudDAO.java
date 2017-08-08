package br.com.opencare.dao;

import java.io.Serializable;

public interface CrudDAO<T, ID extends Serializable> {

	public <S extends T> S save(S entity);

	public T find(ID id);

	public Iterable<T> findAll();

	public long count();

	public void delete(ID id);

	public void delete(T entity);

}
