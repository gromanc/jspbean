package jspbean.hibernate.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Credential entity.
 */
@Entity
@Table(name = "credentials", catalog = "jspbean")
public class Credential extends AbstractCredential implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Credential() {
	}

	/** full constructor */
	public Credential(Set<UserCredential> userCredentials) {
		super(userCredentials);
	}

}
