package br.com.opencare.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.opencare.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <S extends User> S save(S entity) {
		getSession().saveOrUpdate(entity);
		return (S) getSession().get(User.class, entity.getId());
	}

	@Override
	public User findOne(Long id) {
		return getSession().get(User.class, id);
	}

	@Override
	public void delete(Long id) {
		User u = getSession().get(User.class, id);
		getSession().delete(u);
	}

	@Override
	public void delete(User entity) {
		getSession().delete(entity);
	}

	@Override
	public Iterable<User> findAll() {
		Criteria criteria = getSession().createCriteria(User.class);
		return criteria.list();
	}
}
