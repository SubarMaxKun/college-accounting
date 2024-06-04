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
import org.shevliakov.collegeaccounting.appcore.export.ExportEmployeesToExcel;
import org.shevliakov.collegeaccounting.appcore.export.ExportLecturersToExcel;
import org.shevliakov.collegeaccounting.appcore.export.ExportStudentsToExcel;
import org.shevliakov.collegeaccounting.appcore.stage.EditEmployeeInfoStage;
import org.shevliakov.collegeaccounting.appcore.stage.EditLecturerInfoStage;
import org.shevliakov.collegeaccounting.appcore.stage.EditStudentInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.EmployeeTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.LecturerTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.StudentTabSubController;
import org.shevliakov.collegeaccounting.appcore.util.PrintTable;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.EmployeeRepository;
import org.shevliakov.collegeaccounting.database.repository.LecturerRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main controller for the main scene of the application.
 */
public class MainController implements Initializable {

  @FXML
  private ChoiceBox<QualificationCategory> lecturerCategoryChoiceBox;
  @FXML
  private ChoiceBox<PedagogicalTitle> lecturerTitleChoiceBox;
  @FXML
  private ChoiceBox<Integer> lecturerNextCertificationChoiceBox;
  @FXML
  private TextField lecturerNameTextField;
  @FXML
  private TableView<Lecturer> lecturersTableView;
  @FXML
  private TableColumn<?, ?> lecturerFullNameColumn1;
  @FXML
  private TableColumn<?, ?> lecturerPosition;
  @FXML
  private TableColumn<Lecturer, String> lecturerCategory;
  @FXML
  private TableColumn<Lecturer, String> lecturerTitle;
  @FXML
  private TableColumn<?, ?> lecturerLastCertification;
  @FXML
  private TableColumn<?, ?> lecturerNextCertification;
  @FXML
  private TableColumn<?, ?> lecturerHours;
  @FXML
  private TableColumn<?, ?> lecturerCertificate;
  @FXML
  private ChoiceBox<Integer> employeeBirthYearChoiceBox;
  @FXML
  private ChoiceBox<Rank> employeeRankChoiceBox;
  @FXML
  private TextField employeeNameTextField;
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
  @FXML
  private TableColumn<Student, Boolean> studentOnTckColumn;
  @FXML
  private TableColumn<?, ?> studentNotesColumn;
  private StudentTabSubController studentTabSubController;
  private EmployeeTabSubController employeeTabSubController;
  private LecturerTabSubController lecturerTabSubController;

  /**
   * Initializes the controller.
   *
   * @param url            the location used to resolve relative paths for the root object, or null
   *                       if the location is not known
   * @param resourceBundle the resources used to localize the root object, or null if the root
   *                       object was not localized
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Load Spring context
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    // Setup student tab
    studentTabSubController = new StudentTabSubController(studentBirthYearChoiceBox,
        studentGroupChoiceBox, studentNameTextField, studentsTableView, studentFullNameColumn,
        studentDateOfBirthColumn, studentAddressColumn, studentGroupColumn, studentOnTckColumn,
        studentNotesColumn, context.getBean(StudentRepository.class));
    setupStudentsTab();
    // Setup employee tab
    employeeTabSubController = new EmployeeTabSubController(employeeRankChoiceBox,
        employeeBirthYearChoiceBox, employeeNameTextField, employeesTableView, employeeRankColumn,
        employeeFullNameColumn, employeeBirthDateColumn, employeeRegistrationNumberColumn,
        employeeMilitarySpecialtyColumn, employeeTrainingColumn, employeeAccountingCategoryColumn,
        employeeDegreeColumn, employeeIdInfoColumn, context.getBean(EmployeeRepository.class));
    setupEmployeesTab();
    // Setup lecturer tab
    lecturerTabSubController = new LecturerTabSubController(lecturerCategoryChoiceBox,
        lecturerTitleChoiceBox, lecturerNextCertificationChoiceBox, lecturerNameTextField,
        lecturersTableView, lecturerFullNameColumn1, lecturerPosition, lecturerCategory,
        lecturerTitle, lecturerLastCertification, lecturerNextCertification, lecturerHours,
        lecturerCertificate, context.getBean(LecturerRepository.class));
    setupLecturersTab();
  }

  private void setupStudentsTab() {
    studentTabSubController.loadData();
    studentTabSubController.setupTableColumns();
    studentTabSubController.setupFiltering();
  }

  public void onRefreshStudentsButtonClicked() {
    studentTabSubController.refreshData();
  }

  public void onAddStudentButtonClicked() {
    new EditStudentInfoStage().open(Window.getWindows().getFirst(), null, studentTabSubController);
  }

  public void onPrintStudentsButtonClicked() {
    PrintTable printTable = new PrintTable();
    printTable.print(studentsTableView);
  }

  public void onExportStudentsButtonClicked() {
    new ExportStudentsToExcel().export(studentsTableView.getItems());
  }

  private void setupEmployeesTab() {
    employeeTabSubController.loadData();
    employeeTabSubController.setupTableColumns();
    employeeTabSubController.setupFiltering();
  }

  public void onRefreshEmployeesButtonClicked() {
    employeeTabSubController.refreshData();
  }

  public void onAddEmployeeButtonClicked() {
    new EditEmployeeInfoStage().open(Window.getWindows().getFirst(), null,
        employeeTabSubController);
  }

  public void onPrintEmployeesButtonClicked() {
    PrintTable printTable = new PrintTable();
    printTable.print(employeesTableView);
  }

  public void onExportEmployeesButtonClicked() {
    new ExportEmployeesToExcel().export(employeesTableView.getItems());
  }

  private void setupLecturersTab() {
    lecturerTabSubController.loadData();
    lecturerTabSubController.setupTableColumns();
    lecturerTabSubController.setupFiltering();
  }

  public void onRefreshLecturersButtonClicked() {
    lecturerTabSubController.refreshData();
  }

  public void onAddLecturerButtonClicked() {
    new EditLecturerInfoStage().open(Window.getWindows().getFirst(), null,
        lecturerTabSubController);
  }

  public void onPrintLecturersButtonClicked() {
    PrintTable printTable = new PrintTable();
    printTable.print(lecturersTableView);
  }

  public void onExportLecturersButtonClicked() {
    new ExportLecturersToExcel().export(lecturersTableView.getItems());
  }

  public void onUpdateNextCertificationButtonClicked() {
    lecturerTabSubController.updateNextCertification();
  }
}
