package jspbean.hibernate.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Data access object (DAO) for domain model
 */

public class BaseHibernateDAO {
  private static final Log log = LogFactory.getLog(BaseHibernateDAO.class);

  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
  }

 	public Session getSession() {
 		return sessionFactory.getCurrentSession();
 	}

}