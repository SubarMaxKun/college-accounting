package org.shevliakov.collegeaccounting.appcore.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.entity.Person;

public class SearchPersonByName {

  public <T extends Person> void search(TextField nameTextField, List<T> people,
      ObservableList<T> peopleObservableList) {
    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        peopleObservableList.clear();
        peopleObservableList.addAll(people);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        peopleObservableList.clear();
        peopleObservableList.addAll(people);
        peopleObservableList.removeIf(person ->
            !person.getFullName().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }
}

