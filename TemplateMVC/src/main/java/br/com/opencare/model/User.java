package br.com.opencare.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

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

	@Column(length = 10, nullable = false)
	private String state = State.ACTIVE.getState();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	@Version
	@Column(nullable = false)
	protected Timestamp timestamp;

	public User() {
	}

	@AssertTrue(message = "passVerify field should be equal than pass field")
	private boolean isValid() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return this.pwd.equals(passwordEncoder.encode(this.confirmPwd));
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public void setId(long id) {
		this.id = id;
	}

}