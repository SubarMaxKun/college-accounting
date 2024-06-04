package org.shevliakov.collegeaccounting.appcore.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.shevliakov.collegeaccounting.appcore.scene.ChangeToAdmin;
import org.shevliakov.collegeaccounting.appcore.scene.ChangeToMain;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserHasNoPermissionsException;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;
import org.shevliakov.collegeaccounting.security.ValidateUser;
import org.shevliakov.collegeaccounting.security.ValidateUserPassword;

/**
 * Controller for the authorization scene.
 */
public class AuthorizationController {

  @FXML
  private Button authorizationButton;
  @FXML
  private Text registrationText;
  @FXML
  private PasswordField passwordPasswordField;
  @FXML
  private TextField usernameTextField;

  @FXML
  private void onAuthorizationButtonClicked() throws IOException {
    User user = new ValidateUser().validateUser(usernameTextField.getText());
    // Validate username and password and throw alerts if needed.
    if (user == null) {
      new UserWithUsernameNotFoundException(usernameTextField.getText()).showAlert(
          usernameTextField.getText());
    } else if (new ValidateUserPassword().validateUserPassword(user,
        passwordPasswordField.getText())) {
      if (user.getAdmin()) {
        ChangeToAdmin.changeToAdmin((Stage) authorizationButton.getScene().getWindow());
      } else if (user.getReadAndWritePermission()) {
        ChangeToMain.changeToMain((Stage) authorizationButton.getScene().getWindow());
      } else {
        new UserHasNoPermissionsException(usernameTextField.getText()).showAlert(
            usernameTextField.getText());
      }
    }
  }

  @FXML
  private void onRegistrationTextClicked() throws IOException {
    // Load registration scene.
    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/org/shevliakov/collegeaccounting/view/registration-view.fxml"));
    Stage stage = (Stage) registrationText.getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}
