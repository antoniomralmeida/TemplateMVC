package br.com.opencare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.opencare.dao.UserProfileDao;
import br.com.opencare.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	UserProfileDao dao;

	@Autowired
	public void setUserProfiledao(UserProfileDao dao) {
		this.dao = dao;
	}

	@Override
	public <S extends UserProfile> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public UserProfile findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Iterable<UserProfile> findAll() {
		return dao.findAll();
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public void delete(UserProfile entity) {
		dao.delete(entity);
	}

}
