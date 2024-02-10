package org.shevliakov.collegeaccounting.database.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.shevliakov.collegeaccounting.database.DatabaseManager;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;

public class UserRepositoryImpl implements UserRepository {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public List<User> getAllUsers() {
    TypedQuery<User> query = entityManager.createQuery(
        "SELECT u FROM User u", User.class);
    return query.getResultList();
  }

  @Override
  public User getUserById(Long id) {
    return entityManager.find(User.class, id);
  }

  @Override
  public User getUserByUsername(String username) throws UserWithUsernameNotFoundException {
    TypedQuery<User> query = entityManager.createQuery(
        "SELECT u FROM User u WHERE u.username = :username", User.class);
    query.setParameter("username", username);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      throw new UserWithUsernameNotFoundException(username);
    }
  }

  @Override
  public void persistUser(User user) {
    if (user.getId() == null) {
      entityManager.persist(user);
      entityManager.getTransaction().commit();
    } else {
      updateUser(user);
    }
  }

  @Override
  public void updateUser(User user) {
    entityManager.merge(user);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteUser(User user) {
    entityManager.remove(user);
    entityManager.getTransaction().commit();
  }
}
