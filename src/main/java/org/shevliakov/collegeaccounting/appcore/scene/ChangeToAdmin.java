package org.shevliakov.collegeaccounting.appcore.scene;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeToAdmin {

  private ChangeToAdmin() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static void changeToAdmin(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        ChangeToAdmin.class.getResource("/org/shevliakov/collegeaccounting/view/admin-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("АРМ помічника директора із кадрових питань (Адміністратор)");
    stage.setScene(scene);
    stage.show();
  }
}
