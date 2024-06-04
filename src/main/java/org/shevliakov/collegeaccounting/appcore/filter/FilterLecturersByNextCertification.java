package org.shevliakov.collegeaccounting.appcore.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;

/**
 * Filter lecturers by category.
 */
public class FilterLecturersByNextCertification {

  public void filter(ChoiceBox<Integer> nextCertificationChoiceBox,
      ChoiceBox<PedagogicalTitle> titleChoiceBox,
      ChoiceBox<QualificationCategory> categoryChoiceBox, TextField nameTextField,
      List<Lecturer> lecturers, ObservableList<Lecturer> lecturerObservableList) {

    nextCertificationChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(Integer integer) {
        return integer == null ? "Всі" : integer.toString();
      }

      @Override
      public Integer fromString(String s) {
        return null;
      }
    });

    nextCertificationChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
          } else {
            categoryChoiceBox.getSelectionModel().clearSelection();
            titleChoiceBox.getSelectionModel().clearSelection();
            nameTextField.clear();
            lecturerObservableList.clear();
            lecturerObservableList.addAll(lecturers);
            lecturerObservableList.removeIf(
                lecturer -> !lecturer.getNextCertification().equals(newValue));
          }
        });
  }

}
