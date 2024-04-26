package org.shevliakov.collegeaccounting.security;

import java.util.Optional;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
      var context = new AnnotationConfigApplicationContext(SpringConfig.class);
      return Optional.of(context.getBean(UserRepository.class).getByUsername(username));
    } catch (UserWithUsernameNotFoundException e) {
      return Optional.empty();
    }
  }
}
