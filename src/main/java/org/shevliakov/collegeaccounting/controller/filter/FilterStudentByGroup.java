package org.shevliakov.collegeaccounting.controller.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class FilterStudentByGroup {

  public void filter(ChoiceBox<Group> groupChoiceBox, List<Student> students, ObservableList<Student> studentsObservableList) {
    groupChoiceBox.setConverter(new StringConverter<Group>() {
      @Override
      public String toString(Group group) {
        return group == null ? "Всі" : group.getCode();
      }

      @Override
      public Group fromString(String s) {
        return null;
      }
    });

    groupChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
          } else {
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
            studentsObservableList.removeIf(student -> !student.getGroup().equals(newValue));
          }
        });
  }
}
