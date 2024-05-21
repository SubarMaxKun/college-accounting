package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class MilitarySpecialtyCanNotBeEmpty extends IllegalArgumentException{

  public MilitarySpecialtyCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Військово-облікова спеціальність не може бути пустою");
    alert.showAndWait();
  }

}
