package br.com.opencare.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EntityTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 693285990096758852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Version
	@Column(nullable = false)
	protected Timestamp timestamp;

	@PrePersist
	@PostUpdate
	protected void updateTimeStamps() {
		timestamp = new Timestamp(new Date().getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
