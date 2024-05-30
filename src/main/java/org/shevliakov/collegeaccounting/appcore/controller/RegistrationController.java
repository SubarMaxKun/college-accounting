package org.shevliakov.collegeaccounting.appcore.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.exception.PasswordOrUsernameIsWrongException;
import org.shevliakov.collegeaccounting.exception.UserWithUsernameExistsException;
import org.shevliakov.collegeaccounting.security.Hash;
import org.shevliakov.collegeaccounting.security.ValidateUser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Controller for registration view.
 */
public class RegistrationController {

  private static final String USERNAME_PATTERN = "^\\S{4,}$";
  private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
  @FXML
  private Button registrationButton;
  @FXML
  private Text authorizationText;
  @FXML
  private PasswordField passwordPasswordField;
  @FXML
  private TextField usernameTextField;

  /**
   * Validates user input and if it is correct, saves user to database.
   */
  @FXML
  private void onRegistrationButtonClicked() {
    User user = new ValidateUser().validateUser(usernameTextField.getText());

    if (!usernameTextField.getText().matches(USERNAME_PATTERN) || !passwordPasswordField.getText()
        .matches(PASSWORD_PATTERN)) {
      new PasswordOrUsernameIsWrongException("Пароль або логін невірні").showAlert();
    } else if (user != null) {
      new UserWithUsernameExistsException("Користувач із таким логіном вже існує").showAlert();
    } else {
      try {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(
            new User(null, usernameTextField.getText(), Hash.hash(passwordPasswordField.getText()),
                false, false));
      } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setContentText(e.getMessage());
      }
    }
  }

  /**
   * Changes scene to authorization view.
   *
   * @throws IOException if authorization-view.fxml is not found
   */
  @FXML
  private void onAuthorizationTextClicked() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("/org/shevliakov/collegeaccounting/view/authorization-view.fxml"));
    Stage stage = (Stage) authorizationText.getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}
