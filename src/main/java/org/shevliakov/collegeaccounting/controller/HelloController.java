package org.shevliakov.collegeaccounting.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.shevliakov.collegeaccounting.database.dao.impl.UserDaoImpl;

public class HelloController {

  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    UserDaoImpl userDao = new UserDaoImpl();
    if (userDao.getUserByUsername("admin") != null) {
      welcomeText.setText("Hello, admin!");
    } else {
      welcomeText.setText("Hello, user!");
    }
  }
}