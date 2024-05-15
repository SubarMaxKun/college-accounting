package org.shevliakov.collegeaccounting.appcore.scene;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for changing scene to admin-view.fxml.
 */
public class ChangeToAdmin {

  /**
   * Private constructor to prevent instantiation.
   */
  private ChangeToAdmin() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Changes scene to admin-view.fxml.
   *
   * @param stage stage to change scene
   * @throws IOException if an I/O error occurs.
   */
  public static void changeToAdmin(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        ChangeToAdmin.class.getResource("/org/shevliakov/collegeaccounting/view/admin-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}
