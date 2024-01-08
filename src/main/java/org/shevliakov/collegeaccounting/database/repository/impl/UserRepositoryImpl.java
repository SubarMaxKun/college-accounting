package org.shevliakov.collegeaccounting.database.repository.impl;

import org.shevliakov.collegeaccounting.database.dao.impl.UserDaoImpl;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public User getUserById(Long id) {
    return new UserDaoImpl().getUserById(id);
  }

  @Override
  public User getUserByUsername(String username) {
    return new UserDaoImpl().getUserByUsername(username);
  }

  @Override
  public void persistUser(User user) {
    new UserDaoImpl().persistUser(user);
  }

  @Override
  public void updateUser(User user) {
    new UserDaoImpl().updateUser(user);
  }

  @Override
  public void deleteUser(User user) {
    new UserDaoImpl().deleteUser(user);
  }
}
