package br.com.opencare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.opencare.dao.UserDao;
import br.com.opencare.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	UserDao userdao;

	@Autowired
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public <S extends User> S save(S entity) {
		return userdao.save(entity);
	}

	@Override
	public User findOne(Long id) {
		return userdao.findOne(id);
	}

	@Override
	public Iterable<User> findAll() {
		return userdao.findAll();
	}

	@Override
	public void delete(Long id) {
		userdao.delete(id);
	}

	@Override
	public void delete(User entity) {
		userdao.delete(entity);
	}

	@Override
	public Boolean login(String email, String pwd) {
		return userdao.login(email, pwd);
	}
}