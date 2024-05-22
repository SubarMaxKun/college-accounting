package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Exception that is thrown when group already exists.
 */
public class GroupExists extends Exception {

  /**
   * Constructor that calls the super constructor with a message.
   *
   * @param message message of the exception
   */
  public GroupExists(String message) {
    super(message);
  }

  /**
   * Shows an alert with an error message.
   */
  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Введіть іншу групу");
    alert.showAndWait();
  }
}
