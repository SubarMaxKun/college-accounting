package org.shevliakov.collegeaccounting.security;

import java.util.Objects;
import javafx.scene.control.Alert;
import org.shevliakov.collegeaccounting.entity.User;

/**
 * Validate user password.
 */
public class ValidateUserPassword {

  /**
   * Validate user password.
   *
   * @param user     user
   * @param password password
   * @return true if hash of both passwords are equal
   */
  public boolean validateUserPassword(User user, String password) {
    if (!Objects.equals(user.getPassword(), Hash.hash(password))) {
      var alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Password is wrong");
      alert.showAndWait();
    } else {
      return true;
    }
    return false;
  }
}
