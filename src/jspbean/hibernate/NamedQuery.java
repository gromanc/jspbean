package jspbean.hibernate;

import jspbean.hibernate.persistence.BaseHibernateDAO;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class NamedQuery  extends BaseHibernateDAO {
  public void getNamedQuery(){
    Session session = getSession();
    Query query = session.createQuery("from User user");
    int k =10;
    List li1 = query.setMaxResults(k).list();
  }
}
