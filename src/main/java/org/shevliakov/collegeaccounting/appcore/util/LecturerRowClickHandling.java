package org.shevliakov.collegeaccounting.appcore.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.collegeaccounting.appcore.stage.EditLecturerInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.LecturerTabSubController;
import org.shevliakov.collegeaccounting.entity.Lecturer;

public class LecturerRowClickHandling {

  public void rowClickHandling(TableView<Lecturer> tableView,
      LecturerTabSubController lecturerTabSubController) {
    tableView.setRowFactory(tv -> {
      TableRow<Lecturer> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {
          Lecturer clickedRow = row.getItem();
          new EditLecturerInfoStage().open(row.getScene().getWindow(), clickedRow,
              lecturerTabSubController);
        }
      });
      return row;
    });
  }
}


