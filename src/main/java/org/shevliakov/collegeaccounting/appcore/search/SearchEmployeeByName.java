package org.shevliakov.collegeaccounting.appcore.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.entity.Employee;

/**
 * Class for searching worker by full name.
 */
public class SearchEmployeeByName {

  /**
   * Method for searching worker by full name.
   *
   * @param nameTextField         TextField for entering the full name of the worker.
   * @param employees               List of workers.
   * @param workersObservableList ObservableList of workers.
   */
  public void search(TextField nameTextField, List<Employee> employees,
      ObservableList<Employee> workersObservableList) {
    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        workersObservableList.clear();
        workersObservableList.addAll(employees);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        workersObservableList.clear();
        workersObservableList.addAll(employees);
        workersObservableList.removeIf(
            student -> !student.getFullName().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }
}
