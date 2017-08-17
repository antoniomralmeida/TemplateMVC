package br.com.opencare.dao;

import java.io.Serializable;

public interface BASICDAO<T, ID extends Serializable> {
	public <S extends T> S save(S entity);

	public Iterable<T> findAll();

	public long count();
}