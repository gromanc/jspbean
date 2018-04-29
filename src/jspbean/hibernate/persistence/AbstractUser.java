package jspbean.hibernate.persistence;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Long id;

	private String name;
	private Set<UserCredential> userCredentials = new HashSet<UserCredential>(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** full constructor */
	public AbstractUser(String name, Set<UserCredential> userCredentials) {
		this.name = name;
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

//  @JSON(serialize = false, deserialize = false) - this prevent from output name field values
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  @IndexColumn(name="credentials_ORDER")
	public Set<UserCredential> getUserCredentials() {
		return this.userCredentials;
	}

	public void setUserCredentials(Set<UserCredential> userCredentials) {
		this.userCredentials = userCredentials;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractUser)) return false;

    AbstractUser that = (AbstractUser) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (userCredentials != null ? !userCredentials.equals(that.userCredentials) : that.userCredentials != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (userCredentials != null ? userCredentials.hashCode() : 0);
    return result;
  }
}