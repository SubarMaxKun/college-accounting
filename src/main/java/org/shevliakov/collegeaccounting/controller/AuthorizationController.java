package org.shevliakov.collegeaccounting.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.shevliakov.collegeaccounting.security.Authorize;

public class AuthorizationController {

  @FXML
  private Text registrationText;
  @FXML
  private TextField passwordTextField;
  @FXML
  private TextField usernameTextField;

  @FXML
  private void onAuthorizationButtonClicked() {
    if (Authorize.authorize(usernameTextField.getText(), passwordTextField.getText())) {
      System.out.println("Authorized");
    } else {
      System.out.println("Not authorized");
    }
  }

  @FXML
  private void onRegistrationTextClicked() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/org/shevliakov/collegeaccounting/view/registration-view.fxml"));
    Stage stage = (Stage) registrationText.getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Реєстрація");
    stage.setScene(scene);
    stage.show();
  }
}
