package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class UserWithUsernameExistsException extends Exception {

  public UserWithUsernameExistsException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Користувач із таким логіном вже існує");
    alert.setContentText("Введіть інший логін");
    alert.showAndWait();
  }
}
