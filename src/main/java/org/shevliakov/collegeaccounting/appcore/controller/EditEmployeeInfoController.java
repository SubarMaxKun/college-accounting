package org.shevliakov.collegeaccounting.appcore.controller;

import java.sql.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.EmployeeTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.CheckEmployeeInfo;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.EmployeeRepository;
import org.shevliakov.collegeaccounting.database.repository.RankRepository;
import org.shevliakov.collegeaccounting.database.repository.TrainingRepository;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Training;
import org.shevliakov.collegeaccounting.exception.FieldEmptyNullPointerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Controller for the edit employee info scene.
 */
public class EditEmployeeInfoController {

  @FXML
  private TextField fullNameTextField;
  @FXML
  private ChoiceBox<String> rankChoiceBox;
  @FXML
  private DatePicker birthDateDatePicker;
  @FXML
  private TextField addressOfLivingTextField;
  @FXML
  private TextField addressOfRegistrationTextField;
  @FXML
  private TextField tckNameTextField;
  @FXML
  private TextArea familyInfoTextArea;
  @FXML
  private TextField jobInfoTextField;
  @FXML
  private TextField registrationNumberTextField;
  @FXML
  private TextField militarySpecialtyTextField;
  @FXML
  private ChoiceBox<String> trainingChoiceBox;
  @FXML
  private TextField accountingCategoryTextField;
  @FXML
  private TextField degreeTextField;
  @FXML
  private TextArea idInfoTextArea;
  @FXML
  private Button cancelButton;
  @FXML
  private Button commitButton;
  @FXML
  private Button deleteButton;
  private Employee employee;
  private EmployeeRepository employeeRepository;
  private RankRepository rankRepository;
  private TrainingRepository trainingRepository;
  private EmployeeTabSubController employeeTabSubController;

  public void initialize(Employee employee, EmployeeTabSubController employeeTabSubController) {
    // If employee is not null, then we are editing an existing employee, otherwise we are adding a new one
    if (employee != null) {
      this.employee = employee;
      setWorkerInfo();
    } else {
      commitButton.setText("Додати");
      deleteButton.setVisible(false);
    }

    this.employeeTabSubController = employeeTabSubController;

    // Initialize repositories
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    employeeRepository = context.getBean(EmployeeRepository.class);
    rankRepository = context.getBean(RankRepository.class);
    trainingRepository = context.getBean(TrainingRepository.class);

    // Fill choice boxes with data
    List<Rank> ranks = rankRepository.findAll();
    for (Rank rank : ranks) {
      rankChoiceBox.getItems().add(rank.getName());
    }
    List<Training> trainings = trainingRepository.findAll();
    for (Training training : trainings) {
      trainingChoiceBox.getItems().add(training.getName());
    }
  }

  private void setWorkerInfo() {
    fullNameTextField.setText(employee.getFullName());
    rankChoiceBox.setValue(employee.getRank().getName());
    birthDateDatePicker.setValue(employee.getBirthDate().toLocalDate());
    addressOfLivingTextField.setText(employee.getAddressOfLiving());
    addressOfRegistrationTextField.setText(employee.getAddressOfRegistration());
    tckNameTextField.setText(employee.getTckName());
    familyInfoTextArea.setText(employee.getFamily());
    jobInfoTextField.setText(employee.getJobInfo());
    registrationNumberTextField.setText(employee.getRegistrationNumber());
    militarySpecialtyTextField.setText(employee.getMilitarySpecialty());
    trainingChoiceBox.setValue(employee.getTraining().getName());
    accountingCategoryTextField.setText(employee.getAccountingCategory());
    degreeTextField.setText(employee.getDegree());
    idInfoTextArea.setText(employee.getIdInfo());
  }

  @FXML
  private void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void onCommitButtonClicked() {
    Employee employeeToPersist = new Employee();
    // Set employee info
    if (employee != null) {
      employeeToPersist.setId(employee.getId());
    }
    employeeToPersist.setFullName(fullNameTextField.getText());
    employeeToPersist.setRank(rankRepository.getByName(rankChoiceBox.getValue()));
    try {
      employeeToPersist.setBirthDate(Date.valueOf(birthDateDatePicker.getValue()));
    } catch (NullPointerException e) {
      new FieldEmptyNullPointerException("Дата народження не може бути пустою").showAlert();
      return;
    }
    employeeToPersist.setAddressOfLiving(addressOfLivingTextField.getText());
    employeeToPersist.setAddressOfRegistration(addressOfRegistrationTextField.getText());
    employeeToPersist.setTckName(tckNameTextField.getText());
    employeeToPersist.setFamily(familyInfoTextArea.getText());
    employeeToPersist.setJobInfo(jobInfoTextField.getText());
    employeeToPersist.setRegistrationNumber(registrationNumberTextField.getText());
    employeeToPersist.setMilitarySpecialty(militarySpecialtyTextField.getText());
    employeeToPersist.setTraining(trainingRepository.getByName(trainingChoiceBox.getValue()));
    employeeToPersist.setAccountingCategory(accountingCategoryTextField.getText());
    employeeToPersist.setDegree(degreeTextField.getText());
    employeeToPersist.setIdInfo(idInfoTextArea.getText());
    // If the employee info is not valid, then we don't save it
    if (Boolean.FALSE.equals(new CheckEmployeeInfo().check(employeeToPersist))) {
      return;
    }
    // Save the employee to the database
    employeeRepository.save(employeeToPersist);
    employeeTabSubController.refreshData();
    commitButton.getScene().getWindow().hide();
  }

  @FXML
  private void onDeleteButtonClicked() {
    employeeRepository.delete(employee);
    employeeTabSubController.refreshData();
    deleteButton.getScene().getWindow().hide();
  }
}
