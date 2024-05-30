package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class FilterStudentsByGroup {

  public void filter(ChoiceBox<Group> groupChoiceBox, ChoiceBox<Integer> yearChoiceBox,
      TextField nameTextField, List<Student> students,
      ObservableList<Student> studentsObservableList) {
    groupChoiceBox.setConverter(new StringConverter<>() {
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
            yearChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            studentsObservableList.clear();
            studentsObservableList.addAll(students);
            studentsObservableList.removeIf(student -> !student.getGroup().equals(newValue));
          }
        });
  }
}
