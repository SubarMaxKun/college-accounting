package org.shevliakov.collegeaccounting.appcore.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.controller.EditStudentInfoController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.StudentTabSubController;
import org.shevliakov.collegeaccounting.entity.Student;

public class EditStudentInfoStage {

  public void open(Window owner, Student student, StudentTabSubController studentTabSubController) {
    Stage stage = new Stage();
    stage.setResizable(true);
    stage.getIcons().add(new Image("org/shevliakov/collegeaccounting/images/icon.png"));
    stage.setTitle("Інформація про студента");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/org/shevliakov/collegeaccounting/view/edit-student-info-view.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(loader.getRoot());
    stage.setScene(scene);
    EditStudentInfoController controller = loader.getController();
    controller.initialize(student, studentTabSubController);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

}
