package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;

public class FilterLecturersByCategory {

  public void filter(ChoiceBox<QualificationCategory> categoryChoiceBox,
      ChoiceBox<PedagogicalTitle> titleChoiceBox,
      TextField nameTextField, List<Lecturer> lecturers,
      ObservableList<Lecturer> lecturerObservableList) {

    categoryChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(QualificationCategory category) {
        return category == null ? "Всі" : category.getName();
      }

      @Override
      public QualificationCategory fromString(String s) {
        return null;
      }
    });

    categoryChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
          } else {
            titleChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
            lecturerObservableList.removeIf(lecturer -> !lecturer.getCategory().equals(newValue));
          }
        });
  }
}
