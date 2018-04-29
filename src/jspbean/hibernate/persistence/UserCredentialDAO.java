package jspbean.hibernate.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.Example.create;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserCredential entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see UserCredential
 */

@Repository
@Transactional(readOnly = true)
public class UserCredentialDAO  extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserCredentialDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

  @Transactional
	public void save(UserCredential transientInstance) {
		log.debug("saving UserCredential instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

  @Transactional
	public void delete(UserCredential persistentInstance) {
		log.debug("deleting UserCredential instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserCredential findById(Long id) {
		log.debug("getting UserCredential instance with id: " + id);
		try {
			UserCredential instance = (UserCredential) getSession().get(
					"jspbean.hibernate.persistence.UserCredential", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

  public List<UserCredential> findByExample(UserCredential instance) {
 		log.debug("finding UserCredential instance by example");
 		try {
 			List<UserCredential> results = (List<UserCredential>) getSession()
 					.createCriteria("jspbean.hibernate.persistence.UserCredential")
 					.add(create(instance)).list();
 			log.debug("find by example successful, result size: " + results.size());
 			return results;
 		} catch (RuntimeException re) {
 			log.error("find by example failed", re);
 			throw re;
 		}
 	}

  public List<UserCredential> findByProperty(String propertyName, Object value) {
 		log.debug("finding UserCredential instance with property: " + propertyName
 				+ ", value: " + value);
 		try {
 			String queryString = "from UserCredential as model where model."
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
		log.debug("finding all UserCredential instances");
		try {
			String queryString = "from UserCredential";
      Query queryObject = getSession().createQuery(queryString);
 			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

  @Transactional
	public UserCredential merge(UserCredential detachedInstance) {
		log.debug("merging UserCredential instance");
		try {
			UserCredential result = (UserCredential) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

  @Transactional
	public void attachDirty(UserCredential instance) {
		log.debug("attaching dirty UserCredential instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

  @Transactional
	public void attachClean(UserCredential instance) {
		log.debug("attaching clean UserCredential instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserCredentialDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserCredentialDAO) ctx.getBean("UserCredentialDAO");
	}
}