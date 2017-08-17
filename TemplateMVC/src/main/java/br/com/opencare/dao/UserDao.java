package br.com.opencare.dao;

import br.com.opencare.model.User;

public interface UserDao extends CRUDDAO<User, Long> {

	public User findByEmail(String email);

}
