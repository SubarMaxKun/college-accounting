package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class RankExistsException extends Exception {

  public RankExistsException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Таке звання вже існує");
    alert.setContentText("Введіть інше звання");
    alert.showAndWait();
  }
}
