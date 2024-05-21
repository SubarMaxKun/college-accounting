package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class TrainingCanNotBeEmpty extends IllegalArgumentException{

  public TrainingCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Склад підготовки не може бути пустим");
    alert.showAndWait();
  }

}