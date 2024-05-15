package org.shevliakov.collegeaccounting.exception;

import javafx.scene.control.Alert;

/**
 * Exception that is thrown when password or username is wrong.
 */
public class PasswordOrUsernameWrong extends Exception{

    /**
     * Constructor that calls the super constructor with a message.
     *
     * @param message message of the exception
     */
    public PasswordOrUsernameWrong(String message) {
      super(message);
    }

    /**
     * Shows an alert with an error message.
     */
    public void showAlert() {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Помилка");
      alert.setHeaderText(getMessage());
      alert.setContentText("""
          Пароль повинен бути довше 8 символів і містити щонайменше одну цифру, один символ,
          одну літеру у нижньому регістрі та одну літеру у верхньому регістрі.
          Ім'я користувача повинно бути довше 4 символів і не містити пробілів.""");
      alert.showAndWait();
    }

}
