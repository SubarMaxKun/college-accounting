package org.shevliakov.collegeaccounting.appcore.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.controller.EditLecturerInfoController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.LecturerTabSubController;
import org.shevliakov.collegeaccounting.entity.Lecturer;

public class EditLecturerInfoStage {

  public void open(Window owner, Lecturer lecturer,
      LecturerTabSubController lecturerTabSubController) {
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.getIcons().add(new Image("org/shevliakov/collegeaccounting/images/icon.png"));
    stage.setTitle("Інформація про викладача");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/org/shevliakov/collegeaccounting/view/edit-lecturer-info-view.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(loader.getRoot());
    stage.setScene(scene);
    EditLecturerInfoController controller = loader.getController();
    controller.initialize(lecturer, lecturerTabSubController);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

}
