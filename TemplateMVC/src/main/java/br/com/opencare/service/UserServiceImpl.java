package br.com.opencare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.opencare.dao.UserDao;
import br.com.opencare.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	UserDao dao;

	@Autowired
	public void setUserdao(UserDao userdao) {
		this.dao = userdao;
	}

	@Override
	public <S extends User> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public User findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Iterable<User> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public void delete(User entity) {
		dao.delete(entity);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

}