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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
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
	@Email
	@NotEmpty
	private String email;
	@Column(length = 60, nullable = false)
	@NotEmpty
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Min 8, upper, lower, digit and special char.")
	private String pwd;

	@Transient
	private String confirmPwd;

	@AssertTrue(message = "passVerify field should be equal than pass field")
	private boolean isValid() {
		return this.pwd.equals(this.confirmPwd);
	}

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