package org.shevliakov.collegeaccounting.appcore.scene;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeToMain {

  private ChangeToMain() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static void changeToMain(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        ChangeToMain.class.getResource("/org/shevliakov/collegeaccounting/view/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("АРМ помічника директора із кадрових питань");
    stage.setScene(scene);
    stage.show();
  }
}
