package br.com.opencare.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
// @NamedQuery(name = "CMAttribute.findByEntity", query = "SELECT c FROM
// CMAttribute c where c.entity.id = ?1")
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1746304252392913576L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 40, nullable = false)
	@Size(min = 10, message = "Enter at least 10 Characters.")
	private String name;
	@Column(length = 40, nullable = false, unique = true)
	private String email;
	@Column(length = 60, nullable = false)
	@Size(min = 6, message = "Enter at least 6 Characters.")
	private String pwd;

	@Transient
	private String confirmPwd;

	@Version
	@Column(nullable = false)
	protected Timestamp timestamp;

	@PrePersist
	@PostUpdate
	protected void updateTimeStamps() {
		timestamp = new Timestamp(new Date().getTime());
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}