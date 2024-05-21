package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class DegreeCanNotBeEmpty extends IllegalArgumentException{

  public DegreeCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Освіта не може бути пустою");
    alert.showAndWait();
  }

}
