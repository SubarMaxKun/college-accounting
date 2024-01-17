package org.shevliakov.collegeaccounting.security;

import org.shevliakov.collegeaccounting.database.repository.impl.UserRepositoryImpl;
import org.shevliakov.collegeaccounting.entity.User;

public class Authorize {
  public static boolean authorize(String username, String password) {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    User user = userRepository.getUserByUsername(username);
    return user != null && user.getPassword().equals(Hash.hash(password));
  }
}
