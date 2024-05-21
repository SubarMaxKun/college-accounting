package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class BirthDateCanNotBeEmpty extends NullPointerException{

  public BirthDateCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Будь ласка, введіть коректну дату народження");
    alert.showAndWait();
  }

}
