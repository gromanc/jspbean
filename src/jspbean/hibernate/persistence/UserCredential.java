package jspbean.hibernate.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserCredential entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_credential", catalog = "jspbean")
public class UserCredential extends AbstractUserCredential implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserCredential() {
	}

	/** full constructor */
	public UserCredential(User user, Credential credential) {
		super(user, credential);
	}

}
