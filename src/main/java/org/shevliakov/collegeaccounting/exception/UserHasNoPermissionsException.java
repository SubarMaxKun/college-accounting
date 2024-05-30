package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class UserHasNoPermissionsException extends Exception {

  public UserHasNoPermissionsException(String username) {
    super("User with username " + username + " has no permissions");
  }

  public void showAlert(String username) {
    var alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Користувач не має доступу до даних");
    alert.setContentText("Користувач " + username + " не має доступу до даних");
    alert.showAndWait();
  }
}
