package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Exception that is thrown when a user has no permissions.
 */
public class UserHasNoPermissions extends Exception {

  /**
   * Constructor that calls the super constructor with a message.
   *
   * @param username username of the user that has no permissions.
   */
  public UserHasNoPermissions(String username) {
    // Call the super constructor with a message
    super("User with username " + username + " has no permissions");
  }

  /**
   * Shows an alert with an error message.
   *
   * @param username username of the user that is not found
   */
  public void showAlert(String username) {
    // Create an alert of type ERROR
    var alert = new Alert(Alert.AlertType.ERROR);
    // Set the title, header and content text
    alert.setTitle("Помилка");
    alert.setHeaderText("Користувач не має доступу до даних");
    alert.setContentText("Користувач " + username + " не має доступу до даних");
    // Show the alert and wait for the user's response
    alert.showAndWait();
  }
}
