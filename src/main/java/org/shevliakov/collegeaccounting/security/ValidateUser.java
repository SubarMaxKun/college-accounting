package org.shevliakov.collegeaccounting.security;

import java.util.Optional;
import org.shevliakov.collegeaccounting.database.repository.impl.UserRepositoryImpl;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;

/**
 * Class to validate user existence.
 */
public class ValidateUser {

  /**
   * Validate user existence.
   *
   * @param username user name.
   * @return {@link Optional} of {@link User}.
   */
  public Optional<User> validateUser(String username) {
    try {
      return Optional.of(new UserRepositoryImpl().getUserByUsername(username));
    } catch (UserWithUsernameNotFoundException e) {
      return Optional.empty();
    }
  }
}
