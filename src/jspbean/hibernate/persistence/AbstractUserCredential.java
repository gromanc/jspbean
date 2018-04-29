package jspbean.hibernate.persistence;

import javax.persistence.*;

/**
 * AbstractUserCredential entity provides the base persistence definition of the
 * UserCredential entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUserCredential implements java.io.Serializable {

	// Fields

	private Long id;
	private jspbean.hibernate.persistence.User user;
	private jspbean.hibernate.persistence.Credential credential;

	// Constructors

	/** default constructor */
	public AbstractUserCredential() {
	}

	/** full constructor */
	public AbstractUserCredential(jspbean.hibernate.persistence.User user, jspbean.hibernate.persistence.Credential credential) {
		this.user = user;
		this.credential = credential;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public jspbean.hibernate.persistence.User getUser() {
		return this.user;
	}

	public void setUser(jspbean.hibernate.persistence.User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id")
	public jspbean.hibernate.persistence.Credential getCredential() {
		return this.credential;
	}

	public void setCredential(jspbean.hibernate.persistence.Credential credential) {
		this.credential = credential;
	}

}