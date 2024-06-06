package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class PedagogicalTitleExistsException extends Exception {

  public PedagogicalTitleExistsException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Таке пеагогічне звання вже існує");
    alert.setContentText("Введіть інше педагогічне звання");
    alert.showAndWait();
  }
}
