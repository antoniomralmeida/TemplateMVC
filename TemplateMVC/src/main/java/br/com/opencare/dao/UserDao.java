package br.com.opencare.dao;

import br.com.opencare.model.User;

public interface UserDao extends CrudDAO<User, Long> {

	public Boolean login(String email, String pwd);

}
