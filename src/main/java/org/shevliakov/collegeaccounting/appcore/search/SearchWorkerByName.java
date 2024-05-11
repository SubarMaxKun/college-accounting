package org.shevliakov.collegeaccounting.appcore.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.entity.Worker;

/**
 * Class for searching worker by full name.
 */
public class SearchWorkerByName {

  /**
   * Method for searching worker by full name.
   *
   * @param nameTextField         TextField for entering the full name of the worker.
   * @param workers               List of workers.
   * @param workersObservableList ObservableList of workers.
   */
  public void search(TextField nameTextField, List<Worker> workers,
      ObservableList<Worker> workersObservableList) {
    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        workersObservableList.clear();
        workersObservableList.addAll(workers);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        workersObservableList.clear();
        workersObservableList.addAll(workers);
        workersObservableList.removeIf(
            student -> !student.getFullName().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }
}
