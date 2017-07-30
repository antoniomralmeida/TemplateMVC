package br.com.opencare.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.opencare.model.User;
import br.com.opencare.model.UserProfile;
import br.com.opencare.model.UserProfileType;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private UserProfile getProfile(String type) {
		TypedQuery<UserProfile> query = getSession().createQuery("select up from UserProfile up where type = :type");
		query.setParameter("type", type);
		if (query.getResultList().size() == 0)
			return new UserProfile(type);
		else
			return query.getSingleResult();
	}

	@Override
	public <S extends User> S save(S entity) {

		List<String> types = new ArrayList<String>();
		if (entity.getUserProfiles().size() == 0) {
			if (count() == 0)
				types.add(UserProfileType.SYSADMIN.getUserProfileType());
		}
		for (UserProfile up : entity.getUserProfiles())
			types.add(up.getType());

		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		for (String type : types)
			userProfiles.add(getProfile(type));
		entity.setUserProfiles(userProfiles);

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
	public long count() {
		return getSession().createQuery("select count(1) from  User", Long.class).getSingleResult();
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = getSession().createQuery("select u from User u where email = :email");
		query.setParameter("email", email);

		if (query.getResultList().size() == 0)
			return null;
		else
			return query.getSingleResult();
	}
}
