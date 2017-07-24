package br.com.opencare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.opencare.model.User;
import br.com.opencare.model.UserProfile;
import br.com.opencare.model.UserProfileType;

@Repository
public class UserDaoImpl implements UserDao {

	static public List<UserProfile> profiles = new ArrayList<UserProfile>();

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <S extends User> S save(S entity) {

		if (profiles.size() == 0)
			for (int i = 0; i < UserProfileType.values().length; i++)
				profiles.add(new UserProfile(UserProfileType.values()[i].getUserProfileType()));

		if (entity.getUserProfiles().size() == 0) {
			if (count() == 0)
				entity.getUserProfiles().add(profiles.get(UserProfileType.SYSADMIN.ordinal()));

			else
				entity.getUserProfiles().add(profiles.get(UserProfileType.USER.ordinal()));
		}
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
	public User login(String email, String pwd) {
		TypedQuery<User> query = getSession().createQuery("select u from User u where email = :email and pwd = :pwd");
		query.setParameter("email", email);
		query.setParameter("pwd", pwd);

		if (query.getResultList().size() == 0)
			return null;
		else
			return query.getSingleResult();
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
