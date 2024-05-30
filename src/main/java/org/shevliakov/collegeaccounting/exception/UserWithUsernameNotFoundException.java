package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Custom exception that is thrown when user with specified username is not found.
 */
public class UserWithUsernameNotFoundException extends RuntimeException {

  /**
   * Constructor that calls the super constructor with a message.
   *
   * @param username username of the user that is not found
   */
  public UserWithUsernameNotFoundException(String username) {
    // Call the super constructor with a message
    super("User with username " + username + " not found");
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
    alert.setHeaderText("Користувач не знайдений");
    alert.setContentText("Користувача із логіном " + username + " не знайдено");
    // Show the alert and wait for the user's response
    alert.showAndWait();
  }
}
