package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;

public class FilterLecturersByTitle {

  public void filter(ChoiceBox<PedagogicalTitle> titleChoiceBox,
      ChoiceBox<QualificationCategory> categoryChoiceBox,
      ChoiceBox<Integer> nextCertificationChoiceBox, TextField nameTextField,
      List<Lecturer> lecturers, ObservableList<Lecturer> lecturerObservableList) {

    titleChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(PedagogicalTitle title) {
        return title == null ? "Всі" : title.getName();
      }

      @Override
      public PedagogicalTitle fromString(String s) {
        return null;
      }
    });

    titleChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
          } else {
            categoryChoiceBox.getSelectionModel().clearSelection();
            nextCertificationChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
            lecturerObservableList.removeIf(lecturer -> !lecturer.getTitle().equals(newValue));
          }
        });
  }
}
