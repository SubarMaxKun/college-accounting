package org.shevliakov.collegeaccounting.controller.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.collegeaccounting.controller.stage.EditWorkerInfoStage;
import org.shevliakov.collegeaccounting.entity.Worker;

public class WorkerRowClickHandling {

  public void rowClickHandling(TableView<Worker> tableView) {
    tableView.setRowFactory(tv -> {
      TableRow<Worker> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {
          Worker clickedRow = row.getItem();
          new EditWorkerInfoStage().open(row.getScene().getWindow(), clickedRow);
        }
      });
      return row;
    });
  }
}


