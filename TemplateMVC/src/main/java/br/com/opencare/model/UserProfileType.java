package br.com.opencare.model;

public enum UserProfileType {
	USER("USER"), MANAGER("MANAGER"), SYSADMIN("SYSADMIN");

	private String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}