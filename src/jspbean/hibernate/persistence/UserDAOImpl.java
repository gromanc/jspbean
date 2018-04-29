package jspbean.hibernate.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hibernate.criterion.Example.create;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @see User
 */

@Repository
@Transactional(readOnly = true)
public class UserDAOImpl  implements UserDAO {
  private static final Log log = LogFactory.getLog(UserDAOImpl.class);
  // property constants
  public static final String NAME = "name";

  @Autowired
  public UserDAOImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private SessionFactory sessionFactory;

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  protected void initDao() {
    // do nothing
  }

  @Transactional
  public void save(User transientInstance) {
    log.debug("saving User instance");
    try {
      getSession().save(transientInstance);
      log.debug("save successful");
    } catch (RuntimeException re) {
      log.error("save failed", re);
      throw re;
    }
  }

  @Transactional
  public void delete(User persistentInstance) {
    log.debug("deleting User instance");
    try {
      getSession().delete(persistentInstance);
      log.debug("delete successful");
    } catch (RuntimeException re) {
      log.error("delete failed", re);
      throw re;
    }
  }

  public User findById(Long id) {
    log.debug("getting User instance with id: " + id);
    try {
      User instance = (User) getSession().get(
        "jspbean.hibernate.persistence.User", id);
      return instance;
    } catch (RuntimeException re) {
      log.error("get failed", re);
      throw re;
    }
  }

  public List<User> findByExample(User instance) {
 		log.debug("finding User instance by example");
 		try {
 			List<User> results = (List<User>) getSession()
 					.createCriteria("jspbean.hibernate.persistence.User")
 					.add(create(instance)).list();
 			log.debug("find by example successful, result size: " + results.size());
 			return results;
 		} catch (RuntimeException re) {
 			log.error("find by example failed", re);
 			throw re;
 		}
 	}

  public List findByProperty(String propertyName, Object value) {
    log.debug("finding User instance with property: " + propertyName
      + ", value: " + value);
    try {
      String queryString = "from User as model where model." + propertyName
        + "= ?";
      Query queryObject = getSession().createQuery(queryString);
    	queryObject.setParameter(0, value);
 			return queryObject.list();
    } catch (RuntimeException re) {
      log.error("find by property name failed", re);
      throw re;
    }
  }

  public List<User> findByName(Object name) {
    return findByProperty(NAME, name);
  }

  public List findAll() {
    log.debug("finding all User instances");
    try {
      String queryString = "from User";
      Query queryObject = getSession().createQuery(queryString);
 			return queryObject.list();
    } catch (RuntimeException re) {
      log.error("find all failed", re);
      throw re;
    }
  }

  @Transactional
  public User merge(User detachedInstance) {
    log.debug("merging User instance");
    try {
      User result = (User) getSession().merge(detachedInstance);
      log.debug("merge successful");
      return result;
    } catch (RuntimeException re) {
      log.error("merge failed", re);
      throw re;
    }
  }

  @Transactional
  public void attachDirty(User instance) {
    log.debug("attaching dirty User instance");
    try {
      getSession().saveOrUpdate(instance);
      log.debug("attach successful");
    } catch (RuntimeException re) {
      log.error("attach failed", re);
      throw re;
    }
  }

  @Transactional
  public void attachClean(User instance) {
    log.debug("attaching clean User instance");
    try {
      getSession().buildLockRequest(LockOptions.NONE).lock(instance);
      log.debug("attach successful");
    } catch (RuntimeException re) {
      log.error("attach failed", re);
      throw re;
    }
  }

  public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
    return (UserDAO) ctx.getBean("jspbean.hibernate.persistence.UserDAO");
  }

  @SuppressWarnings("unchecked")
  public List<User> getUsers(){
    try {
      Set<Long> userIds = new HashSet<Long>();
      userIds.add(1l);
      userIds.add(2l);

//          String whereClause = "from User where id IN (:userIds) ";
//          Query query = session.createQuery(whereClause);
//          query.setParameterList("userIds", userIds);
      Criteria criteria = getSession().createCriteria(User.class);
      criteria.add(Restrictions.in("id", userIds));
      return criteria.list();
    } catch (RuntimeException re) {
      log.error("getUsers failed", re);
      throw re;
    }
  }

  @SuppressWarnings("unchecked")
  public List<User> findByCriteria(final DetachedCriteria dc, final int from, final int size)
  {
    if (log.isDebugEnabled()) log.debug("Return Users from " + from + " to " + size);
    try {
//            List l =  session.createCriteria(UserCredential.class).createCriteria("user","u", Criteria.LEFT_JOIN).list();
      Criteria criteria = dc.getExecutableCriteria(getSession());
      criteria.setFirstResult(from);
      criteria.setMaxResults(size);
      List result = criteria.list();

      return result;
    } catch (RuntimeException re) {
      log.error("findByCriteria failed", re);
      throw re;
    }
  }

  public int countByCriteria(final DetachedCriteria dc)
  {
    if (log.isDebugEnabled()) log.debug("count Users");
    try {
      Criteria criteria = dc.getExecutableCriteria(getSession());
      criteria.setProjection(Projections.rowCount());
      return Integer.parseInt(criteria.list().get(0).toString());
    } catch (RuntimeException re) {
      log.error("findByCriteria failed", re);
      throw re;
    }
  }

}