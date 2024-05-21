package org.shevliakov.collegeaccounting.appcore.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.collegeaccounting.appcore.stage.EditEmployeeInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.EmployeeTabSubController;
import org.shevliakov.collegeaccounting.entity.Employee;

public class EmployeeRowClickHandling {

  public void rowClickHandling(TableView<Employee> tableView, EmployeeTabSubController employeeTabSubController) {
    tableView.setRowFactory(tv -> {
      TableRow<Employee> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {
          Employee clickedRow = row.getItem();
          new EditEmployeeInfoStage().open(row.getScene().getWindow(), clickedRow, employeeTabSubController);
        }
      });
      return row;
    });
  }
}


