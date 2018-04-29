package jspbean.hibernate.persistence;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface UserDAO {
  int countByCriteria(DetachedCriteria criteria);

  List<User> findByCriteria(DetachedCriteria criteria, int from, int rows);

  List<User> getUsers();

  User findById(Long id);
}
