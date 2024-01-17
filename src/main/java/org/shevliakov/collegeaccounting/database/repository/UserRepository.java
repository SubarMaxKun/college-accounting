package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;

public interface UserRepository {

  User getUserById(Long id);

  User getUserByUsername(String username) throws UserWithUsernameNotFoundException;

  void persistUser(User user);

  void updateUser(User user);

  void deleteUser(User user);

}
