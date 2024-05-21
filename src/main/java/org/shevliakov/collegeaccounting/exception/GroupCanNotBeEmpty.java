package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class GroupCanNotBeEmpty extends IllegalArgumentException {

  public GroupCanNotBeEmpty(String message) {
    super(message);
  }

  public void showAllert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Група не може бути пустою");
    alert.showAndWait();
  }
}
