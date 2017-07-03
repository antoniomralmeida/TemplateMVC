package br.com.opencare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
// @NamedQuery(name = "CMAttribute.findByEntity", query = "SELECT c FROM
// CMAttribute c where c.entity.id = ?1")
public class User extends EntityTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1481771056872902462L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	private String email;
	private String pwd;

	@Transient
	private String confirmPwd;

	public User() {
	}

	public User(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}