package org.shevliakov.collegeaccounting.appcore.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.stage.EditEmployeeInfoStage;
import org.shevliakov.collegeaccounting.appcore.stage.EditStudentInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.EmployeeTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.StudentTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.PrintTable;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.EmployeeRepository;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController implements Initializable {

  @FXML
  private ChoiceBox<Integer> employeeBirthYearChoiceBox;
  @FXML
  private ChoiceBox<Rank> employeeRankChoiceBox;
  @FXML
  private TextField workerNameTextField;
  @FXML
  private TableView<Employee> employeesTableView;
  @FXML
  private TableColumn<Employee, String> employeeRankColumn;
  @FXML
  private TableColumn<?, ?> employeeFullNameColumn;
  @FXML
  private TableColumn<?, ?> employeeBirthDateColumn;
  @FXML
  private TableColumn<?, ?> employeeRegistrationNumberColumn;
  @FXML
  private TableColumn<?, ?> employeeMilitarySpecialtyColumn;
  @FXML
  private TableColumn<Employee, String> employeeTrainingColumn;
  @FXML
  private TableColumn<?, ?> employeeAccountingCategoryColumn;
  @FXML
  private TableColumn<?, ?> employeeDegreeColumn;
  @FXML
  private TableColumn<?, ?> employeeIdInfoColumn;
  @FXML
  private TextField studentNameTextField;
  @FXML
  private ChoiceBox<Integer> studentBirthYearChoiceBox;
  @FXML
  private ChoiceBox<Group> studentGroupChoiceBox;
  @FXML
  private TableView<Student> studentsTableView;
  @FXML
  private TableColumn<?, ?> studentFullNameColumn;
  @FXML
  private TableColumn<?, ?> studentDateOfBirthColumn;
  @FXML
  private TableColumn<?, ?> studentAddressColumn;
  @FXML
  private TableColumn<Student, String> studentGroupColumn;
  private StudentTabSubController studentTabSubController;
  private EmployeeTabSubController employeeTabSubController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    studentTabSubController = new StudentTabSubController(studentBirthYearChoiceBox,
        studentGroupChoiceBox, studentNameTextField, studentsTableView, studentFullNameColumn,
        studentDateOfBirthColumn, studentAddressColumn, studentGroupColumn,
        context.getBean(StudentRepository.class), context.getBean(GroupRepository.class));
    setupStudentsTab();

    employeeTabSubController = new EmployeeTabSubController(employeeRankChoiceBox,
        employeeBirthYearChoiceBox, workerNameTextField, employeesTableView, employeeRankColumn,
        employeeFullNameColumn, employeeBirthDateColumn, employeeRegistrationNumberColumn,
        employeeMilitarySpecialtyColumn, employeeTrainingColumn, employeeAccountingCategoryColumn,
        employeeDegreeColumn, employeeIdInfoColumn, context.getBean(EmployeeRepository.class));
    setupWorkersTab();
  }

  private void setupStudentsTab() {
    studentTabSubController.loadData();
    studentTabSubController.setupTableColumns();
    studentTabSubController.setupFiltering();
  }

  private void setupWorkersTab() {
    employeeTabSubController.loadData();
    employeeTabSubController.setupTableColumns();
    employeeTabSubController.setupFiltering();
  }

  public void onRefreshStudentsButtonClicked() {
    studentTabSubController.refreshData();
  }

  public void onAddStudentButtonClicked() {
    new EditStudentInfoStage().open(Window.getWindows().getFirst(), null);
  }

  public void onRefreshEmployeesButtonClicked() {
    employeeTabSubController.refreshData();
  }

  public void onAddEmployeeButtonClicked() {
    new EditEmployeeInfoStage().open(Window.getWindows().getFirst(), null);
  }

  public void onPrintStudentsButtonClicked() {
    PrintTable printTable = new PrintTable();
    printTable.print(studentsTableView);
  }

  public void onPrintEmployeesButtonClicked() {
    PrintTable printTable = new PrintTable();
    printTable.print(employeesTableView);
  }
}