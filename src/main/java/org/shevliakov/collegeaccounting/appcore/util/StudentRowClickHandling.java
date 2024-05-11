package org.shevliakov.collegeaccounting.appcore.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.collegeaccounting.appcore.stage.EditStudentInfoStage;
import org.shevliakov.collegeaccounting.entity.Student;

public class StudentRowClickHandling {

  public void rowClickHandling(TableView<Student> tableView) {
    tableView.setRowFactory(tv -> {
      TableRow<Student> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {
          Student clickedRow = row.getItem();
          new EditStudentInfoStage().open(row.getScene().getWindow(), clickedRow);
        }
      });
      return row;
    });
  }
}


