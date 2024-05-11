package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Worker;

public class FilterWorkerByRank {

  public void filter(ChoiceBox<Rank> rankChoiceBox, ChoiceBox<Integer> birthYearChoiceBox,
      List<Worker> workers, ObservableList<Worker> workersObservableList) {
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
            workersObservableList.addAll(workers);
          } else {
            birthYearChoiceBox.getSelectionModel().clearSelection();
            workersObservableList.clear();
            workersObservableList.addAll(workers);
            workersObservableList.removeIf(worker -> !worker.getRank().equals(newValue));
          }
        });
  }

}
