package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Exception that is thrown when rank already exists.
 */
public class RankExists extends Exception {

  /**
   * Constructor that calls the super constructor with a message.
   *
   * @param message message of the exception
   */
  public RankExists(String message) {
    super(message);
  }

  /**
   * Shows an alert with an error message.
   */
  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Введіть інше звання");
    alert.showAndWait();
  }
}
