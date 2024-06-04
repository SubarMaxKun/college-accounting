package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class UserWithUsernameNotFoundException extends RuntimeException {

  public UserWithUsernameNotFoundException(String username) {
    // Call the super constructor with a message
    super("User with username " + username + " not found");
  }

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
