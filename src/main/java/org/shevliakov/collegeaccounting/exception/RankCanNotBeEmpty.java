package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class RankCanNotBeEmpty extends IllegalArgumentException{

  public RankCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Звання не може бути пустим");
    alert.showAndWait();
  }

}
