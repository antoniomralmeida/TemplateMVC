package br.com.opencare.dao;

import java.io.Serializable;

public interface CRUDDAO<T, ID extends Serializable> {

	public <S extends T> S save(S entity);

	public T find(ID id);

	public Iterable<T> findAll();

	public Iterable<T> findByCriteria(String criteria, int page);

	public long countByCriteria(String criteria);

	public long count();

	public void delete(ID id);

	public void delete(T entity);

}
