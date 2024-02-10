package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Exception that is thrown when user with username already exists.
 */
public class UserWithUsernameExists extends Exception {

  /**
   * Constructor that calls the super constructor with a message.
   *
   * @param message message of the exception
   */
  public UserWithUsernameExists(String message) {
    super(message);
  }

  /**
   * Shows an alert with an error message.
   */
  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("User with this username already exists");
    alert.showAndWait();
  }
}
