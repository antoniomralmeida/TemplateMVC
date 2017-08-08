package br.com.opencare.dao;

import br.com.opencare.model.User;
import br.com.opencare.model.UserProfile;

public interface UserProfileDao extends CrudDAO<UserProfile, Long> {
	public void setupUserProfiles();
}
