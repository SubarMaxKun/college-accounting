package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class RegistrationNumberCanNotBeEmpty extends IllegalArgumentException{

  public RegistrationNumberCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Обліковий номер/РНОКПП не може бути пустим");
    alert.showAndWait();
  }

}
