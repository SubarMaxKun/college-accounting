package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterEmployeesByBirthYear;
import org.shevliakov.collegeaccounting.appcore.filter.FilterEmployeesByRank;
import org.shevliakov.collegeaccounting.appcore.search.SearchPersonByName;
import org.shevliakov.collegeaccounting.appcore.util.ConvertDatesToYears;
import org.shevliakov.collegeaccounting.appcore.util.EmployeeRowClickHandling;
import org.shevliakov.collegeaccounting.database.repository.EmployeeRepository;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Rank;

/**
 * Subcontroller for the Employee tab.
 */
public class EmployeeTabSubController {

  private final ChoiceBox<Rank> rankChoiceBox;
  private final ChoiceBox<Integer> birthYearChoiceBox;
  private final TextField nameSearchTextField;
  private final TableView<Employee> workersTableView;
  private final TableColumn<Employee, String> rankColumn;
  private final TableColumn<?, ?> fullNameColumn;
  private final TableColumn<?, ?> birthDateColumn;
  private final TableColumn<?, ?> employeeAddressOfLivingColumn;
  private final TableColumn<?, ?> employeeAddressOfRegistrationColumn;
  private final TableColumn<?, ?> employeeTckNameColumn;
  private final TableColumn<?, ?> employeeFamilyColumn;
  private final TableColumn<?, ?> employeeJobInfoColumn;
  private final TableColumn<?, ?> registrationNumberColumn;
  private final TableColumn<?, ?> militarySpecialtyColumn;
  private final TableColumn<Employee, String> trainingColumn;
  private final TableColumn<?, ?> accountingCategoryColumn;
  private final TableColumn<?, ?> degreeColumn;
  private final TableColumn<?, ?> idInfoColumn;
  private final EmployeeRepository employeeRepository;
  private List<Employee> employees;
  private ObservableList<Employee> workersObservableList;

  /**
   * Constructor.
   *
   * @param rankChoiceBox            the choice box for selecting ranks
   * @param birthYearChoiceBox       the choice box for selecting years of birth
   * @param nameSearchTextField      the text field for searching by name
   * @param workersTableView         the table view for displaying employees
   * @param rankColumn               the column for displaying ranks
   * @param fullNameColumn           the column for displaying full names
   * @param birthDateColumn          the column for displaying birth dates
   * @param registrationNumberColumn the column for displaying registration numbers
   * @param militarySpecialtyColumn  the column for displaying military specialties
   * @param trainingColumn           the column for displaying training
   * @param accountingCategoryColumn the column for displaying accounting categories
   * @param degreeColumn             the column for displaying degrees
   * @param idInfoColumn             the column for displaying ID info
   * @param employeeRepository       the repository for employees
   */
  public EmployeeTabSubController(ChoiceBox<Rank> rankChoiceBox,
      ChoiceBox<Integer> birthYearChoiceBox, TextField nameSearchTextField,
      TableView<Employee> workersTableView, TableColumn<Employee, String> rankColumn,
      TableColumn<?, ?> fullNameColumn, TableColumn<?, ?> birthDateColumn,
      TableColumn<?, ?> addressOfLivingColumn, TableColumn<?, ?> addressOfRegistrationColumn,
      TableColumn<?, ?> tckNameColumn, TableColumn<?, ?> familyInfoColumn,
      TableColumn<?, ?> jobInfoColumn, TableColumn<?, ?> registrationNumberColumn,
      TableColumn<?, ?> militarySpecialtyColumn, TableColumn<Employee, String> trainingColumn,
      TableColumn<?, ?> accountingCategoryColumn, TableColumn<?, ?> degreeColumn,
      TableColumn<?, ?> idInfoColumn, EmployeeRepository employeeRepository) {
    this.rankChoiceBox = rankChoiceBox;
    this.birthYearChoiceBox = birthYearChoiceBox;
    this.nameSearchTextField = nameSearchTextField;
    this.workersTableView = workersTableView;
    this.rankColumn = rankColumn;
    this.fullNameColumn = fullNameColumn;
    this.birthDateColumn = birthDateColumn;
    this.employeeAddressOfLivingColumn = addressOfLivingColumn;
    this.employeeAddressOfRegistrationColumn = addressOfRegistrationColumn;
    this.employeeTckNameColumn = tckNameColumn;
    this.employeeFamilyColumn = familyInfoColumn;
    this.employeeJobInfoColumn = jobInfoColumn;
    this.registrationNumberColumn = registrationNumberColumn;
    this.militarySpecialtyColumn = militarySpecialtyColumn;
    this.trainingColumn = trainingColumn;
    this.accountingCategoryColumn = accountingCategoryColumn;
    this.degreeColumn = degreeColumn;
    this.idInfoColumn = idInfoColumn;
    this.employeeRepository = employeeRepository;
  }

  /**
   * Load data into the table and choice boxes.
   */
  public void loadData() {
    // Fill the table with data
    employees = employeeRepository.findAll();
    workersObservableList = workersTableView.getItems();
    workersObservableList.addAll(employees);

    // Fill the choice box with years of birth
    List<Integer> years = ConvertDatesToYears.convert(employeeRepository.getDistinctBirthDates());
    birthYearChoiceBox.getItems().add(null);
    birthYearChoiceBox.getItems().addAll(years);

    // Fill the choice box with ranks
    rankChoiceBox.getItems().add(null);
    rankChoiceBox.getItems().addAll(employeeRepository.getDistinctRanks());
  }

  /**
   * Refresh the data in the table and choice boxes.
   */
  public void refreshData() {
    employees.clear();
    workersObservableList.clear();
    rankChoiceBox.getItems().clear();
    birthYearChoiceBox.getItems().clear();
    nameSearchTextField.clear();
    loadData();
    setupFiltering();
    workersTableView.refresh();
  }

  /**
   * Set up filtering for the table.
   */
  public void setupFiltering() {
    new SearchPersonByName().search(nameSearchTextField, employees, workersObservableList);
    new FilterEmployeesByBirthYear().filter(birthYearChoiceBox, rankChoiceBox, nameSearchTextField,
        employees, workersObservableList);
    new FilterEmployeesByRank().filter(rankChoiceBox, birthYearChoiceBox, nameSearchTextField,
        employees, workersObservableList);
    new EmployeeRowClickHandling().rowClickHandling(workersTableView,
        EmployeeTabSubController.this);
  }

  /**
   * Set up table columns.
   */
  public void setupTableColumns() {
    rankColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getRank().getName()));
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    employeeAddressOfLivingColumn.setCellValueFactory(new PropertyValueFactory<>("addressOfLiving"));
    employeeAddressOfRegistrationColumn.setCellValueFactory(new PropertyValueFactory<>("addressOfRegistration"));
    employeeTckNameColumn.setCellValueFactory(new PropertyValueFactory<>("tckName"));
    employeeFamilyColumn.setCellValueFactory(new PropertyValueFactory<>("family"));
    employeeJobInfoColumn.setCellValueFactory(new PropertyValueFactory<>("jobInfo"));
    registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
    militarySpecialtyColumn.setCellValueFactory(new PropertyValueFactory<>("militarySpecialty"));
    trainingColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getTraining().getName()));
    accountingCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("accountingCategory"));
    degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
    idInfoColumn.setCellValueFactory(new PropertyValueFactory<>("idInfo"));
  }
}
