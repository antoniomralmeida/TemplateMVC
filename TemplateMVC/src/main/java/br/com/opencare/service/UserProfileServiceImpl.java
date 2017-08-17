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
	public void setUserdao(UserProfileDao dao) {
		this.dao = dao;
	}

	@Override
	public void setupUserProfiles() {
		dao.setupUserProfiles();
	}

	@Override
	public <S extends UserProfile> S save(S entity) {
		return dao.save(entity);
	}

	@Override
	public Iterable<UserProfile> findAll() {
		return dao.findAll();
	}

	@Override
	public long count() {
		return dao.count();
	}
}
