package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class UserWithUsernameNotFoundException extends RuntimeException {

  // A constructor that takes a username as a parameter
  public UserWithUsernameNotFoundException(String username) {
    // Call the super constructor with a message
    super("User with username " + username + " not found");
  }


  // A method that shows an alert on JavaFX
  public void showAlert(String username) {
    // Create an alert of type ERROR
    var alert = new Alert(Alert.AlertType.ERROR);
    // Set the title, header and content text
    alert.setTitle("Error");
    alert.setHeaderText("User not found");
    alert.setContentText("User with username " + username + " not found");
    // Show the alert and wait for the user's response
    alert.showAndWait();
  }
}
