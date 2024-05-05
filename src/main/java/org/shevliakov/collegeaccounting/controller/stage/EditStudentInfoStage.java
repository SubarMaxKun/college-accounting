package org.shevliakov.collegeaccounting.controller.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.controller.EditStudentInfoController;
import org.shevliakov.collegeaccounting.entity.Student;

public class EditStudentInfoStage {

  public void open(Window owner, Student student) {
    Stage stage = new Stage();
    stage.setResizable(false);
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
    controller.initialize(student);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

}
