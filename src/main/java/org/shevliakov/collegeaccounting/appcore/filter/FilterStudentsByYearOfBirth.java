package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

/**
 * Filter students by group.
 */
public class FilterStudentsByYearOfBirth {

  public void filter(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Group> groupChoiceBox,
      TextField nameTextField, List<Student> students,
      ObservableList<Student> studentsObservableList) {
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

    yearChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
          } else {
            groupChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
            studentsObservableList.removeIf(
                student -> student.getBirthDate().toLocalDate().getYear() != newValue);
          }
        });
  }

}
