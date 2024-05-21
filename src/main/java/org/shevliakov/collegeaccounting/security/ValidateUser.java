package org.shevliakov.collegeaccounting.security;

import java.util.Optional;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;
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
  public User validateUser(String username) {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    return context.getBean(UserRepository.class).getByUsername(username);
  }
}
