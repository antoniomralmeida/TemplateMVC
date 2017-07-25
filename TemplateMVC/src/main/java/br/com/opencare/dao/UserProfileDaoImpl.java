package br.com.opencare.dao;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.opencare.model.UserProfile;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <S extends UserProfile> S save(S entity) {
		getSession().saveOrUpdate(entity);
		return (S) getSession().get(UserProfile.class, entity.getId());
	}

	@Override
	public UserProfile findOne(Long id) {
		return getSession().get(UserProfile.class, id);
	}

	@Override
	public Iterable<UserProfile> findAll() {
		TypedQuery<UserProfile> query = getSession().createQuery("select up from UserProfile up");
		return query.getResultList();
	}

	@Override
	public long count() {
		return getSession().createQuery("select count(1) from  UserProfile", Long.class).getSingleResult();
	}

	@Override
	public void delete(Long id) {
		UserProfile up = getSession().get(UserProfile.class, id);
		getSession().delete(up);
	}

	@Override
	public void delete(UserProfile entity) {
		getSession().delete(entity);
	}
}
