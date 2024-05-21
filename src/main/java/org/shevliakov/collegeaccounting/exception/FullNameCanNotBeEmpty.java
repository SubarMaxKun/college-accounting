package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class FullNameCanNotBeEmpty extends IllegalArgumentException{

  public FullNameCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("ПІБ не можуть бути пустими");
    alert.showAndWait();
  }

}