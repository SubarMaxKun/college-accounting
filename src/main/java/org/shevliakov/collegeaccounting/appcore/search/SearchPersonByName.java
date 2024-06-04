package org.shevliakov.collegeaccounting.appcore.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.entity.Person;

/**
 * Search person by name in the list of people.
 */
public class SearchPersonByName {

  /**
   * Search person by name in the list of people.
   *
   * @param nameTextField         text field for entering the name of the person.
   * @param people                list of people.
   * @param peopleObservableList  observable list of people.
   * @param <T>                   type of person.
   */
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

