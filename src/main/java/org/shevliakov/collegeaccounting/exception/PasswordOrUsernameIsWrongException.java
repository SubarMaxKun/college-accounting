package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

public class PasswordOrUsernameIsWrongException extends Exception {

  public PasswordOrUsernameIsWrongException(String message) {
    super(message);
  }

  public void showAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Помилка");
    alert.setHeaderText("Пароль або логін невірні");
    alert.setContentText("""
        Пароль повинен бути довше 8 символів і містити щонайменше одну цифру, один символ,
        одну літеру у нижньому регістрі та одну літеру у верхньому регістрі.
        Ім'я користувача повинно бути довше 4 символів і не містити пробілів.""");
    alert.showAndWait();
  }

}
