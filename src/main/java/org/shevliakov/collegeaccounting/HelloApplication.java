package org.shevliakov.collegeaccounting;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {

  public static void main(String[] args) {
    launch();
  }

  // TODO: Не забути змінити ресурс для сцени на authorization-view.fxml
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        HelloApplication.class.getResource("view/authorization-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.getIcons().add(new Image("org/shevliakov/collegeaccounting/images/icon.png"));
    stage.setTitle("АРМ помічника директора із кадрових питань");
    stage.setScene(scene);
    stage.show();
  }
}