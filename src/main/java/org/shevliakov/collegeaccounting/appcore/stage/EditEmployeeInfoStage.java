package org.shevliakov.collegeaccounting.appcore.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.controller.EditEmployeeInfoController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.EmployeeTabSubController;
import org.shevliakov.collegeaccounting.entity.Employee;

public class EditEmployeeInfoStage {

  public void open(Window owner, Employee employee,
      EmployeeTabSubController employeeTabSubController) {
    Stage stage = new Stage();
    stage.setResizable(true);
    stage.getIcons().add(new Image("org/shevliakov/collegeaccounting/images/icon.png"));
    stage.setTitle("Інформація про працівника");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/org/shevliakov/collegeaccounting/view/edit-employee-info-view.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(loader.getRoot());
    stage.setScene(scene);
    EditEmployeeInfoController controller = loader.getController();
    controller.initialize(employee, employeeTabSubController);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }
}
