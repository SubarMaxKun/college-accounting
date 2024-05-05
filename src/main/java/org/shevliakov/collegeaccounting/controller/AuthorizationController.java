package org.shevliakov.collegeaccounting.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.shevliakov.collegeaccounting.controller.scene.ChangeToMain;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameNotFoundException;
import org.shevliakov.collegeaccounting.security.ValidateUserPassword;
import org.shevliakov.collegeaccounting.security.ValidateUser;

/**
 * Controller for authorization-view.fxml which is responsible for authorization of user.
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

  /**
   * Proceeds authorization of user when button is clicked.
   */
  @FXML
  private void onAuthorizationButtonClicked() throws IOException {
    Optional<User> user = new ValidateUser().validateUser(usernameTextField.getText());

    if (user.isEmpty()) {
      new UserWithUsernameNotFoundException(usernameTextField.getText()).showAlert(
          usernameTextField.getText());
    } else if (new ValidateUserPassword().validateUserPassword(user.get(),
        passwordPasswordField.getText())) {
      ChangeToMain.changeToMain((Stage) authorizationButton.getScene().getWindow());
    }
  }

  /**
   * Changes scene to registration-view.fxml.
   *
   * @throws IOException if an I/O error occurs.
   */
  @FXML
  private void onRegistrationTextClicked() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/org/shevliakov/collegeaccounting/view/registration-view.fxml"));
    Stage stage = (Stage) registrationText.getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}
