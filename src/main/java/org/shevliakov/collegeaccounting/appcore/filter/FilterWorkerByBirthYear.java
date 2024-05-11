package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Worker;

public class FilterWorkerByBirthYear {

  public void filter(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Rank> rankChoiceBox,
      List<Worker> workers, ObservableList<Worker> workersObservableList) {
    // Initialize converter for yearChoiceBox.
    yearChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(Integer year) {
        return year == null ? "Всі" : year.toString();
      }

      @Override
      public Integer fromString(String s) {
        return null;
      }
    });

    // Add listener to yearChoiceBox.
    yearChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            workersObservableList.clear();
            workersObservableList.addAll(workers);
          } else {
            rankChoiceBox.getSelectionModel().clearSelection();
            workersObservableList.clear();
            workersObservableList.addAll(workers);
            workersObservableList.removeIf(
                worker -> worker.getBirthDate().toLocalDate().getYear() != newValue);
          }
        });
  }
}
