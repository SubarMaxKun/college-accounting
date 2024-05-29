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
import org.shevliakov.collegeaccounting.exception.BirthDateCanNotBeEmpty;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
  private Button commitButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button cancelButton;
  @FXML
  private CheckBox onTckCheckBox;
  @FXML
  private TextArea notesTextArea;
  private Student student;
  private StudentRepository studentRepository;
  private GroupRepository groupRepository;
  private StudentTabSubController studentTabSubController;

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
    Student studentToPersist = new Student();
    if (student != null) {
      studentToPersist.setId(student.getId());
    }
    studentToPersist.setFullName(fullNameTextField.getText());
    try {
      studentToPersist.setBirthDate(java.sql.Date.valueOf(birthDateDatePicker.getValue()));
    } catch (NullPointerException e) {
      new BirthDateCanNotBeEmpty("Дата народження не може бути пустою").showAlert();
      return;
    }
    studentToPersist.setGroup(groupRepository.getByCode(groupChoiceBox.getValue()));
    studentToPersist.setAddress(addressTextArea.getText());
    studentToPersist.setOnTck(onTckCheckBox.isSelected());
    studentToPersist.setNotes(notesTextArea.getText());
    if (Boolean.FALSE.equals(new CheckStudentInfo().check(studentToPersist))) {
      return;
    }
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
