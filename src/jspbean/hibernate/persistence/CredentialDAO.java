package jspbean.hibernate.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.hibernate.criterion.Example.create;

/**
 * A data access object (DAO) providing persistence and search support for
 * Credential entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Credential
 */

@Repository
@Transactional(readOnly = true)
public class CredentialDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CredentialDAO.class);

  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";


	// property constants

	protected void initDao() {
		// do nothing
	}

  @Transactional
	public void save(Credential transientInstance) {
		log.debug("saving Credential instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

  @Transactional
	public void delete(Credential persistentInstance) {
		log.debug("deleting Credential instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Credential findById(Long id) {
		log.debug("getting Credential instance with id: " + id);
		try {
			Credential instance = (Credential) getSession().get(
					"jspbean.hibernate.persistence.Credential", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

  public List<Credential> findByExample(Credential instance) {
 		log.debug("finding Credential instance by example");
 		try {
 			List<Credential> results = (List<Credential>) getSession()
 					.createCriteria("jspbean.hibernate.persistence.Credential")
 					.add(create(instance)).list();
 			log.debug("find by example successful, result size: " + results.size());
 			return results;
 		} catch (RuntimeException re) {
 			log.error("find by example failed", re);
 			throw re;
 		}
 	}


	public List<Credential> findByProperty(String propertyName, Object value) {
		log.debug("finding Credential instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Credential as model where model."
					+ propertyName + "= ?";
   		Query queryObject = getSession().createQuery(queryString);
   		queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Credential instances");
		try {
			String queryString = "from Credential";
      Query queryObject = getSession().createQuery(queryString);
 			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

  @Transactional
	public Credential merge(Credential detachedInstance) {
		log.debug("merging Credential instance");
		try {
			Credential result = (Credential) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

  @Transactional
	public void attachDirty(Credential instance) {
		log.debug("attaching dirty Credential instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

  @Transactional
	public void attachClean(Credential instance) {
		log.debug("attaching clean Credential instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CredentialDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CredentialDAO) ctx.getBean("CredentialDAO");
	}

  public Set<UserCredential> getUserCredentials(final Credential credential){
    log.debug("getUserCredentials Credential instance");
    try {
      Credential cred = (Credential)getSession().load(credential.getClass(),credential.getId());
      Hibernate.initialize(cred.getUserCredentials());
      Set<UserCredential> res = cred.getUserCredentials();
      return res;
    } catch (RuntimeException re) {
      log.error("getUserCredentials failed", re);
      throw re;
    }
  }

  public <T> T initializeAndUnproxy(T entity) {
    if (entity == null) {
      throw new
        NullPointerException("Entity passed for initialization is null");
    }

    Hibernate.initialize(entity);
    if (entity instanceof HibernateProxy) {
      entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
          .getImplementation();
    }
    return entity;
  }
}