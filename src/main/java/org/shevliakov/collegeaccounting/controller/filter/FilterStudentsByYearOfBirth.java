package org.shevliakov.collegeaccounting.controller.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class FilterStudentsByYearOfBirth {

  public void filter(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Group> groupChoiceBox, List<Student> students, ObservableList<Student> studentsObservableList) {
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
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
            studentsObservableList.removeIf(student -> student.getBirthDate().toLocalDate().getYear() != newValue);
          }
        });
  }

}
