package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class QualificationCategoryExistsException extends Exception {

  public QualificationCategoryExistsException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Така категорія вже існує");
    alert.setContentText("Введіть іншу категорію");
    alert.showAndWait();
  }
}
