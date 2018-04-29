package jspbean.hibernate.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "jspbean")
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}


  /** full constructor */
	public User(String name, Set<UserCredential> userCredentials) {
		super(name, userCredentials);
	}

}
