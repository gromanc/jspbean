package jspbean.hibernate.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCredential entity provides the base persistence definition of the
 * Credential entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCredential implements java.io.Serializable {

	// Fields

	private Long id;
  private String username;
  private String password;
	private Set<UserCredential> userCredentials = new HashSet<UserCredential>(0);

	// Constructors

	/** default constructor */
	public AbstractCredential() {
	}

	/** full constructor */
	public AbstractCredential(Set<UserCredential> userCredentials) {
		this.userCredentials = userCredentials;
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
  @Column(name ="username", nullable = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name ="password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "credential")
	public Set<UserCredential> getUserCredentials() {
		return this.userCredentials;
	}

	public void setUserCredentials(Set<UserCredential> userCredentials) {
		this.userCredentials = userCredentials;
	}

}