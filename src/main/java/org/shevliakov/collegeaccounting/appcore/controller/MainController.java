package org.shevliakov.collegeaccounting.appcore.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import org.shevliakov.collegeaccounting.appcore.export.ExportEmployeesToExcel;
import org.shevliakov.collegeaccounting.appcore.export.ExportLecturersToExcel;
import org.shevliakov.collegeaccounting.appcore.export.ExportStudentsToExcel;
import org.shevliakov.collegeaccounting.appcore.stage.EditEmployeeInfoStage;
import org.shevliakov.collegeaccounting.appcore.stage.EditLecturerInfoStage;
import org.shevliakov.collegeaccounting.appcore.stage.EditStudentInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.*;
import org.shevliakov.collegeaccounting.appcore.util.PrintTable;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.*;
import org.shevliakov.collegeaccounting.entity.*;
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
  private TableColumn<?, ?> lecturerEmploymentYear;
  @FXML
  private TableColumn<?, ?> lecturerExperience;
  @FXML
  private TableColumn<?, ?> lecturerPreviousCertificationResult;
  @FXML
  private ChoiceBox<Integer> employeeBirthYearChoiceBox;
  @FXML
  private TableColumn<?, ?> employeeAddressOfLivingColumn;
  @FXML
  private TableColumn<?, ?> employeeAddressOfRegistrationColumn;
  @FXML
  private TableColumn<?, ?> employeeTckNameColumn;
  @FXML
  private TableColumn<?, ?> employeeFamilyColumn;
  @FXML
  private TableColumn<?, ?> employeeJobInfoColumn;
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
  private TableColumn<?, ?> studentMilitaryDocumentColumn;
  @FXML
  private TableColumn<?, ?> studentSpecialtyAndRankColumn;
  @FXML
  private TableColumn<?, ?> studentTckNameColumn;
  @FXML
  private TableColumn<?, ?> studentTaxCardColumn;
  @FXML
  private TableColumn<?, ?> studentNotesColumn;
  @FXML
  private TextField pedagogicalTitleTextField;
  @FXML
  private TableView<PedagogicalTitle> pedagogicalTitlesTableView;
  @FXML
  private TableColumn<?, ?> pedagogicalTitleTableColumn;
  @FXML
  private TableColumn<PedagogicalTitle, Button> deletePedagogicalTitleTableColumn;
  @FXML
  private TextField groupTextField;
  @FXML
  private TableView<Group> groupsTableView;
  @FXML
  private TableColumn<?, ?> groupTableColumn;
  @FXML
  private TableColumn<Group, Button> deleteGroupTableColumn;
  @FXML
  private TextField rankTextField;
  @FXML
  private TableView<Rank> ranksTableView;
  @FXML
  private TableColumn<?, ?> rankTableColumn;
  @FXML
  private TableColumn<Rank, Button> deleteRankTableColumn;
  @FXML
  private TableView<QualificationCategory> qualificationCategoriesTableView;
  @FXML
  private TableColumn<?, ?> qualificationCategoryTableColumn;
  @FXML
  private TableColumn<QualificationCategory, Button> deleteQualificationCategoryTableColumn;
  @FXML
  private TextField qualificationCategoryTextField;
  private StudentTabSubController studentTabSubController;
  private EmployeeTabSubController employeeTabSubController;
  private LecturerTabSubController lecturerTabSubController;
  private RanksTabSubController ranksTabSubController;
  private GroupsTabSubController groupsTabSubController;
  private CategoriesTabSubController categoriesTabSubController;
  private PedagogicalTitlesTabSubController pedagogicalTitlesTabSubController;

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
        studentMilitaryDocumentColumn, studentSpecialtyAndRankColumn, studentTckNameColumn,
        studentTaxCardColumn, studentNotesColumn, context.getBean(StudentRepository.class));
    setupStudentsTab();
    // Setup employee tab
    employeeTabSubController = new EmployeeTabSubController(employeeRankChoiceBox,
        employeeBirthYearChoiceBox, employeeNameTextField, employeesTableView, employeeRankColumn,
        employeeFullNameColumn, employeeBirthDateColumn, employeeAddressOfLivingColumn,
        employeeAddressOfRegistrationColumn, employeeTckNameColumn, employeeFamilyColumn,
        employeeJobInfoColumn, employeeRegistrationNumberColumn, employeeMilitarySpecialtyColumn,
        employeeTrainingColumn, employeeAccountingCategoryColumn, employeeDegreeColumn,
        employeeIdInfoColumn, context.getBean(EmployeeRepository.class));
    setupEmployeesTab();
    // Setup lecturer tab
    lecturerTabSubController = new LecturerTabSubController(lecturerCategoryChoiceBox,
        lecturerTitleChoiceBox, lecturerNextCertificationChoiceBox, lecturerNameTextField,
        lecturersTableView, lecturerFullNameColumn1, lecturerPosition, lecturerEmploymentYear,
        lecturerExperience, lecturerCategory, lecturerTitle, lecturerLastCertification,
        lecturerNextCertification, lecturerPreviousCertificationResult, lecturerHours,
        lecturerCertificate, context.getBean(LecturerRepository.class));
    setupLecturersTab();

    ranksTabSubController = new RanksTabSubController(rankTextField, ranksTableView,
        rankTableColumn, deleteRankTableColumn, context.getBean(RankRepository.class));
    ranksTabSubController.loadData();
    ranksTabSubController.setupTableColumns();

    groupsTabSubController = new GroupsTabSubController(groupTextField, groupsTableView,
        groupTableColumn, deleteGroupTableColumn, context.getBean(GroupRepository.class));
    groupsTabSubController.loadData();
    groupsTabSubController.setupTableColumns();

    categoriesTabSubController = new CategoriesTabSubController(qualificationCategoryTextField,
        qualificationCategoriesTableView, qualificationCategoryTableColumn,
        deleteQualificationCategoryTableColumn,
        context.getBean(QualificationCategoryRepository.class));
    categoriesTabSubController.loadData();
    categoriesTabSubController.setupTableColumns();

    pedagogicalTitlesTabSubController = new PedagogicalTitlesTabSubController(
        pedagogicalTitleTextField, pedagogicalTitlesTableView, pedagogicalTitleTableColumn,
        deletePedagogicalTitleTableColumn, context.getBean(PedagogicalTitleRepository.class));
    pedagogicalTitlesTabSubController.loadData();
    pedagogicalTitlesTabSubController.setupTableColumns();
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

  public void onAddRankButtonClicked() {
    ranksTabSubController.addRank();
  }

  public void onAddGroupButtonClicked() {
    groupsTabSubController.addGroup();
  }

  public void onAddQualificationCategoryButtonClicked() {
    categoriesTabSubController.addQualificationCategory();
  }

  public void onAddPedagogicalTitleButtonClicked() {
    pedagogicalTitlesTabSubController.addPedagogicalTitle();
  }
}
