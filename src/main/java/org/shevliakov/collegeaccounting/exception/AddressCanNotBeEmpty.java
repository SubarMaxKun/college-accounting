package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class AddressCanNotBeEmpty extends IllegalArgumentException{

  public AddressCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Адреса не може бути пустою");
    alert.showAndWait();
  }

}