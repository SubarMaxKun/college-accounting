package org.shevliakov.collegeaccounting.database.dao;

import org.shevliakov.collegeaccounting.entity.User;

public interface UserDao {

  User getUserById(Long id);

  User getUserByUsername(String username);

  void persistUser(User user);

  void updateUser(User user);

  void deleteUser(User user);

}
