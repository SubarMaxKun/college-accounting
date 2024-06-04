package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class GroupExistsException extends Exception {

  public GroupExistsException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Така група вже існує");
    alert.setContentText("Введіть іншу групу");
    alert.showAndWait();
  }
}
