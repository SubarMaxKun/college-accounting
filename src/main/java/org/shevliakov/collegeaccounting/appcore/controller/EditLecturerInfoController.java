package org.shevliakov.collegeaccounting.appcore.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.LecturerTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.CheckLecturerInfo;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.LecturerRepository;
import org.shevliakov.collegeaccounting.database.repository.PedagogicalTitleRepository;
import org.shevliakov.collegeaccounting.database.repository.QualificationCategoryRepository;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Controller for the edit lecturer info scene.
 */
public class EditLecturerInfoController {

  @FXML
  private TextArea certificateTextArea;
  @FXML
  private TextField fullNameTextField;
  @FXML
  private TextField positionTextField;
  @FXML
  private TextField employmentYearTextField;
  @FXML
  private TextArea experienceTextArea;
  @FXML
  private ChoiceBox<String> qualificationCategoryChoiceBox;
  @FXML
  private ChoiceBox<String> pedagogicalTitleChoiceBox;
  @FXML
  private TextField lastCertificationTextField;
  @FXML
  private TextField nextCertificationTextField;
  @FXML
  private TextArea previousCertificationResultTextArea;
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
  private QualificationCategoryRepository qualificationCategoryRepository;
  private PedagogicalTitleRepository pedagogicalTitleRepository;

  /**
   * Initializes the controller with the lecturer and the subcontroller.
   *
   * @param lecturer                 the lecturer to edit
   * @param lecturerTabSubController the subcontroller
   */
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
    qualificationCategoryRepository = context.getBean(QualificationCategoryRepository.class);
    pedagogicalTitleRepository = context.getBean(PedagogicalTitleRepository.class);

    List<QualificationCategory> qualificationCategories = qualificationCategoryRepository.findAll();
    for (QualificationCategory qualificationCategory : qualificationCategories) {
      qualificationCategoryChoiceBox.getItems().add(qualificationCategory.getName());
    }

    List<PedagogicalTitle> pedagogicalTitles = pedagogicalTitleRepository.findAll();
    for (PedagogicalTitle pedagogicalTitle : pedagogicalTitles) {
      pedagogicalTitleChoiceBox.getItems().add(pedagogicalTitle.getName());
    }
  }

  private void setLecturerInfo() {
    fullNameTextField.setText(lecturer.getFullName());
    positionTextField.setText(lecturer.getPosition());
    employmentYearTextField.setText(lecturer.getEmploymentYear().toString());
    experienceTextArea.setText(lecturer.getExperience());
    qualificationCategoryChoiceBox.setValue(lecturer.getCategory().getName());
    pedagogicalTitleChoiceBox.setValue(lecturer.getTitle().getName());
    lastCertificationTextField.setText(String.valueOf(lecturer.getLastCertification()));
    nextCertificationTextField.setText(String.valueOf(lecturer.getNextCertification()));
    previousCertificationResultTextArea.setText(lecturer.getPreviousCertificationResult());
    hoursTextArea.setText(lecturer.getHours());
  }

  public void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  public void onCommitButtonClicked() {
    // Fill data from the form
    Lecturer lecturerToPersist = new Lecturer();
    if (lecturer != null) {
      lecturerToPersist.setId(lecturer.getId());
    }
    lecturerToPersist.setFullName(fullNameTextField.getText());
    lecturerToPersist.setPosition(positionTextField.getText());
    lecturerToPersist.setExperience(experienceTextArea.getText());
    lecturerToPersist.setCategory(
        qualificationCategoryRepository.getByName(qualificationCategoryChoiceBox.getValue()));
    lecturerToPersist.setTitle(
        pedagogicalTitleRepository.getByName(pedagogicalTitleChoiceBox.getValue()));
    if (lastCertificationTextField.getText().isEmpty() || nextCertificationTextField.getText()
        .isEmpty() || employmentYearTextField.getText().isEmpty() || lastCertificationTextField.getText()
        .isBlank() || nextCertificationTextField.getText().isBlank() || employmentYearTextField.getText()
        .isBlank()) {
      new FieldEmptyIllegalArgumentException("Рік не може бути пустим").showAlert();
      return;
    } else if (lastCertificationTextField.getLength() > 4
        || nextCertificationTextField.getLength() > 4 || employmentYearTextField.getLength() > 4) {
      new FieldEmptyIllegalArgumentException("Рік не може бути довшим 4 символів").showAlert();
      return;
    }
    try {
      lecturerToPersist.setLastCertification(
          Integer.parseInt(lastCertificationTextField.getText()));
      lecturerToPersist.setNextCertification(
          Integer.parseInt(nextCertificationTextField.getText()));
      lecturerToPersist.setEmploymentYear(Integer.parseInt(employmentYearTextField.getText()));
    } catch (NumberFormatException e) {
      new FieldEmptyIllegalArgumentException("Рік повинен бути числом").showAlert();
      return;
    }
    lecturerToPersist.setPreviousCertificationResult(previousCertificationResultTextArea.getText());
    lecturerToPersist.setCertificate(certificateTextArea.getText());
    lecturerToPersist.setHours(hoursTextArea.getText());
    // If data is not valid, do not save it
    if (Boolean.FALSE.equals(new CheckLecturerInfo().check(lecturerToPersist))) {
      return;
    }
    // Save the data
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
