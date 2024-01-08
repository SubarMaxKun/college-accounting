package org.shevliakov.collegeaccounting.database.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.shevliakov.collegeaccounting.database.DatabaseManager;
import org.shevliakov.collegeaccounting.database.dao.UserDao;
import org.shevliakov.collegeaccounting.entity.User;

public class UserDaoImpl implements UserDao {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public User getUserById(Long id) {
    return entityManager.find(User.class, id);
  }

  @Override
  public User getUserByUsername(String username) {
    TypedQuery<User> query = entityManager.createQuery(
        "SELECT u FROM User u WHERE u.username = :username", User.class);
    query.setParameter("username", username);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public void persistUser(User user) {
    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();
  }

  @Override
  public void updateUser(User user) {
    entityManager.getTransaction().begin();
    User updatedUser = entityManager.merge(user);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteUser(User user) {
    entityManager.getTransaction().begin();
    entityManager.remove(user);
    entityManager.getTransaction().commit();
  }
}
