package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class FieldEmptyNullPointerException extends NullPointerException {

  public FieldEmptyNullPointerException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Введіть коректні дані");
    alert.showAndWait();
  }

}
