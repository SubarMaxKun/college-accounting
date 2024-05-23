package org.shevliakov.collegeaccounting.appcore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.LecturerTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.CheckLecturerInfo;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.LecturerRepository;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.exception.YearShouldBeNumber;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EditLecturerInfoController {

  @FXML
  private TextField fullNameTextField;
  @FXML
  private TextField positionTextField;
  @FXML
  private TextField lastCertificationTextField;
  @FXML
  private TextField nextCertificationTextField;
  @FXML
  private TextArea hoursTextArea;
  @FXML
  private Button commitButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button cancelButton;
  private Lecturer lecturer;
  private LecturerTabSubController lecturerTabSubController;
  private LecturerRepository lecturerRepository;

  public void initialize(Lecturer lecturer, LecturerTabSubController lecturerTabSubController) {
    if (lecturer != null) {
      this.lecturer = lecturer;
      setLecturerInfo();
    } else {
      commitButton.setText("Додати");
      deleteButton.setVisible(false);
    }
    this.lecturerTabSubController = lecturerTabSubController;

    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    lecturerRepository = context.getBean(LecturerRepository.class);
  }

  private void setLecturerInfo() {
    fullNameTextField.setText(lecturer.getFullName());
    positionTextField.setText(lecturer.getPosition());
    lastCertificationTextField.setText(String.valueOf(lecturer.getLastCertification()));
    nextCertificationTextField.setText(String.valueOf(lecturer.getNextCertification()));
    hoursTextArea.setText(lecturer.getHours());
  }

  public void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  public void onCommitButtonClicked() {
    Lecturer lecturerToPersist = new Lecturer();
    lecturerToPersist.setFullName(fullNameTextField.getText());
    lecturerToPersist.setPosition(positionTextField.getText());
    if (lastCertificationTextField.getText().isEmpty() || nextCertificationTextField.getText()
        .isEmpty() || lastCertificationTextField.getText().isBlank()
        || nextCertificationTextField.getText().isBlank()) {
      new YearShouldBeNumber("Рік не може бути пустим").showAlert();
      return;
    } else if (lastCertificationTextField.getLength() > 4
        || nextCertificationTextField.getLength() > 4) {
      new YearShouldBeNumber("Рік не може бути довшим 4 символів").showAlert();
      return;
    }
    try {
      lecturerToPersist.setLastCertification(
          Integer.parseInt(lastCertificationTextField.getText()));
      lecturerToPersist.setNextCertification(
          Integer.parseInt(nextCertificationTextField.getText()));
    } catch (NumberFormatException e) {
      new YearShouldBeNumber("Рік повинен бути числом").showAlert();
      return;
    }
    lecturerToPersist.setHours(hoursTextArea.getText());
    if (Boolean.FALSE.equals(new CheckLecturerInfo().check(lecturerToPersist))) {
      return;
    }
    lecturerRepository.save(lecturerToPersist);
    lecturerTabSubController.refreshData();
    commitButton.getScene().getWindow().hide();
  }

  public void onDeleteButtonClicked() {
    lecturerRepository.delete(lecturer);
    lecturerTabSubController.refreshData();
    deleteButton.getScene().getWindow().hide();
  }
}
