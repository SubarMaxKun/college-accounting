package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class IdInfoCanNotBeEmpty extends IllegalArgumentException{

  public IdInfoCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Реквізити паспорта України не можуть бути пустими");
    alert.showAndWait();
  }

}
