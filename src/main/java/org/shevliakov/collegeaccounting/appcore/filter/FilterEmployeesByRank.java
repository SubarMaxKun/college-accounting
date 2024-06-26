package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Rank;

/**
 * Filter employees by rank.
 */
public class FilterEmployeesByRank {

  public void filter(ChoiceBox<Rank> rankChoiceBox, ChoiceBox<Integer> birthYearChoiceBox,
      TextField nameTextField, List<Employee> employees,
      ObservableList<Employee> workersObservableList) {
    // Initialize converter for rankChoiceBox.
    rankChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(Rank rank) {
        return rank == null ? "Всі" : rank.getName();
      }

      @Override
      public Rank fromString(String s) {
        return null;
      }
    });

    // Add listener to rankChoiceBox.
    rankChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            workersObservableList.clear();
            workersObservableList.addAll(employees);
          } else {
            birthYearChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            workersObservableList.clear();
            workersObservableList.addAll(employees);
            workersObservableList.removeIf(worker -> !worker.getRank().equals(newValue));
          }
        });
  }

}
