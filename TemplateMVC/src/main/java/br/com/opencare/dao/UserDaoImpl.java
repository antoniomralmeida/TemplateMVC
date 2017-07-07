package br.com.opencare.dao;

import javax.persistence.TypedQuery;

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
		TypedQuery<User> query = getSession().createQuery("select u from User u");
		return query.getResultList();
	}

	@Override
	public Boolean login(String email, String pwd) {
		TypedQuery<User> query = getSession().createQuery("select u from User u where email = :email and pwd = :pwd");
		query.setParameter("email", email);
		query.setParameter("pwd", pwd);
		return query.getResultList().size() > 0;
	}
}
