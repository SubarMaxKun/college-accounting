package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.User;

public interface UserRepository {

  User getUserById(Long id);

  User getUserByUsername(String username);

  void persistUser(User user);

  void updateUser(User user);

  void deleteUser(User user);
}
