package org.shevliakov.collegeaccounting.appcore.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.entity.Student;

public class SearchStudentByName {

  public void search(TextField nameTextField, List<Student> students,
      ObservableList<Student> studentsObservableList) {
    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        studentsObservableList.clear();
        studentsObservableList.addAll(students);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        studentsObservableList.clear();
        studentsObservableList.addAll(students);
        studentsObservableList.removeIf(
            student -> !student.getFullName().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }
}
