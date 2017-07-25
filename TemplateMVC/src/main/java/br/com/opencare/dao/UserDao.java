package br.com.opencare.dao;

import br.com.opencare.model.User;

public interface UserDao extends CrudDAO<User, Long> {

	// public User login(String email, String pwd);

	public User findByEmail(String email);

}
