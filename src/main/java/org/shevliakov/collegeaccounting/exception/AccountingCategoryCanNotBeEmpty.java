package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class AccountingCategoryCanNotBeEmpty extends IllegalArgumentException{

  public AccountingCategoryCanNotBeEmpty(String message){
    super(message);
  }

  public void showAllert(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText(getMessage());
    alert.setContentText("Категорія обліку не може бути пустою");
    alert.showAndWait();
  }

}