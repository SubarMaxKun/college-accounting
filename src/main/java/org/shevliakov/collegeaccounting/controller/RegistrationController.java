package org.shevliakov.collegeaccounting.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.shevliakov.collegeaccounting.database.repository.impl.UserRepositoryImpl;
import org.shevliakov.collegeaccounting.entity.User;
import org.shevliakov.collegeaccounting.security.Hash;
import org.shevliakov.collegeaccounting.security.ValidateUser;

/**
 * Controller for registration view.
 */
public class RegistrationController {

  private static final String USERNAME_PATTERN = "^\\S{4,}$";
  private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
  @FXML
  private Text authorizationText;
  @FXML
  private PasswordField passwordPasswordField;
  @FXML
  private TextField usernameTextField;

  /**
   * Validates user input and if it is correct, saves user to database.
   *
   * @throws IOException if main-view.fxml is not found
   */
  @FXML
  private void onRegistrationButtonClicked() throws IOException {
    // TODO: Refactor this piece of code to make it more readable and maintainable by using
    //   custom exceptions and maybe validators
    Optional<User> user = new ValidateUser().validateUser(usernameTextField.getText());

    if (!usernameTextField.getText().matches(USERNAME_PATTERN) || !passwordPasswordField.getText()
        .matches(PASSWORD_PATTERN)) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Password or username is wrong");
      alert.showAndWait();
    } else if (user.isPresent()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("User with this username already exists");
      alert.showAndWait();
    } else {
      UserRepositoryImpl userRepository = new UserRepositoryImpl();
      userRepository.persistUser(new User(null, usernameTextField.getText(), Hash
          .hash(passwordPasswordField.getText()), false));
      ChangeToMain.changeToMain((Stage) authorizationText.getScene().getWindow());
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
    stage.setTitle("Авторизація");
    stage.setScene(scene);
    stage.show();
  }
}
