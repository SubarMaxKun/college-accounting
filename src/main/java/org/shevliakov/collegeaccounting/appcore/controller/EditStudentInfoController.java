package org.shevliakov.collegeaccounting.appcore.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.StudentTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.CheckStudentInfo;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;
import org.shevliakov.collegeaccounting.exception.FieldEmptyNullPointerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Controller for the edit student info.\
 */
public class EditStudentInfoController {

  @FXML
  private TextField fullNameTextField;
  @FXML
  private DatePicker birthDateDatePicker;
  @FXML
  private ChoiceBox<String> groupChoiceBox;
  @FXML
  private TextArea addressTextArea;
  @FXML
  private CheckBox onTckCheckBox;
  @FXML
  private TextField militaryDocumentTextField;
  @FXML
  private TextField specialtyAndRankTextField;
  @FXML
  private TextField tckNameTextField;
  @FXML
  private TextField taxCardNumberTextField;
  @FXML
  private TextArea notesTextArea;
  @FXML
  private Button commitButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button cancelButton;
  private Student student;
  private StudentRepository studentRepository;
  private GroupRepository groupRepository;
  private StudentTabSubController studentTabSubController;

  /**
   * Initializes the controller.
   *
   * @param student                 the student to edit
   * @param studentTabSubController the student tab sub controller
   */
  public void initialize(Student student, StudentTabSubController studentTabSubController) {
    if (student != null) {
      this.student = student;
      setStudentInfo();
    } else {
      commitButton.setText("Додати");
      deleteButton.setVisible(false);
    }
    this.studentTabSubController = studentTabSubController;

    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    studentRepository = context.getBean(StudentRepository.class);
    groupRepository = context.getBean(GroupRepository.class);

    List<Group> groupCodes = groupRepository.getAll();
    for (Group group : groupCodes) {
      groupChoiceBox.getItems().add(group.getCode());
    }
  }

  private void setStudentInfo() {
    fullNameTextField.setText(student.getFullName());
    birthDateDatePicker.setValue(student.getBirthDate().toLocalDate());
    groupChoiceBox.setValue(student.getGroup().getCode());
    addressTextArea.setText(student.getAddress());
    onTckCheckBox.setSelected(student.getOnTck());
    militaryDocumentTextField.setText(student.getMilitaryDocument());
    if (student.getSpecialtyAndRank() != null) {
      specialtyAndRankTextField.setText(student.getSpecialtyAndRank());
    }
    taxCardNumberTextField.setText(student.getTaxCardNumber());
    tckNameTextField.setText(student.getTckName());
    if (student.getNotes() != null) {
      notesTextArea.setText(student.getNotes());
    }
  }

  @FXML
  private void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void onCommitButtonClicked() {
    // Fill data from the form
    Student studentToPersist = new Student();
    if (student != null) {
      studentToPersist.setId(student.getId());
    }
    studentToPersist.setFullName(fullNameTextField.getText());
    try {
      studentToPersist.setBirthDate(java.sql.Date.valueOf(birthDateDatePicker.getValue()));
    } catch (NullPointerException e) {
      new FieldEmptyNullPointerException("Дата народження не може бути пустою").showAlert();
      return;
    }
    studentToPersist.setGroup(groupRepository.getByCode(groupChoiceBox.getValue()));
    studentToPersist.setAddress(addressTextArea.getText());
    studentToPersist.setOnTck(onTckCheckBox.isSelected());
    studentToPersist.setMilitaryDocument(militaryDocumentTextField.getText());
    studentToPersist.setSpecialtyAndRank(specialtyAndRankTextField.getText());
    studentToPersist.setTaxCardNumber(taxCardNumberTextField.getText());
    studentToPersist.setTckName(tckNameTextField.getText());
    studentToPersist.setNotes(notesTextArea.getText());
    // If the data is not valid, do not save it
    if (Boolean.FALSE.equals(new CheckStudentInfo().check(studentToPersist))) {
      return;
    }
    // Save the data
    studentRepository.save(studentToPersist);
    studentTabSubController.refreshData();
    commitButton.getScene().getWindow().hide();
  }

  @FXML
  private void onDeleteButtonClicked() {
    studentRepository.delete(student);
    studentTabSubController.refreshData();
    deleteButton.getScene().getWindow().hide();
  }
}
