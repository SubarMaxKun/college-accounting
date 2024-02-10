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
      alert.setTitle("Error");
      alert.setHeaderText(getMessage());
      alert.setContentText("""
          Password should be longer than 8 characters and contain at
          least one digit, one symbol, one lowercase and one uppercase letter.
          Username should be longer than 4 characters and contain no spaces.""");
      alert.showAndWait();
    }

}
