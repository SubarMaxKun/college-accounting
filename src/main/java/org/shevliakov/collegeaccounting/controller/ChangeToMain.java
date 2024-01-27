package org.shevliakov.collegeaccounting.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for changing scene to main-view.fxml.
 */
public class ChangeToMain {

  /**
   * Private constructor to prevent instantiation.
   */
  private ChangeToMain() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Changes scene to main-view.fxml.
   *
   * @param stage stage to change scene.
   * @throws IOException if an I/O error occurs.
   */
  public static void changeToMain(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        ChangeToMain.class.getResource("/org/shevliakov/collegeaccounting/view/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Головне меню");
    stage.setScene(scene);
    stage.show();
  }
}
