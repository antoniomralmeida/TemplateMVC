package br.com.opencare.dao;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.opencare.model.UserProfile;
import br.com.opencare.model.UserProfileType;

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
	public Iterable<UserProfile> findAll() {
		TypedQuery<UserProfile> query = getSession().createQuery("select up from UserProfile up");
		return query.getResultList();
	}

	@Override
	public long count() {
		return getSession().createQuery("select count(1) from  UserProfile", Long.class).getSingleResult();
	}

	@Override
	public void setupUserProfiles() {
		if (count() == 0) {
			for (int i = 0; i < UserProfileType.values().length; i++)
				save(new UserProfile(UserProfileType.values()[i].getUserProfileType()));
		}
	}

}
