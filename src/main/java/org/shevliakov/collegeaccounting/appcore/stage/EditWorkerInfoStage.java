package org.shevliakov.collegeaccounting.appcore.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.controller.EditWorkerInfoController;
import org.shevliakov.collegeaccounting.entity.Worker;

public class EditWorkerInfoStage {

  public void open(Window owner, Worker worker) {
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.setTitle("Інформація про працівника");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/org/shevliakov/collegeaccounting/view/edit-worker-info-view.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(loader.getRoot());
    stage.setScene(scene);
    EditWorkerInfoController controller = loader.getController();
    controller.initialize(worker);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }
}
