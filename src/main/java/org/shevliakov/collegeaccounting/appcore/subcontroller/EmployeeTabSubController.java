package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterEmployeesByBirthYear;
import org.shevliakov.collegeaccounting.appcore.filter.FilterEmployeesByRank;
import org.shevliakov.collegeaccounting.appcore.search.SearchEmployeeByName;
import org.shevliakov.collegeaccounting.appcore.util.EmployeeRowClickHandling;
import org.shevliakov.collegeaccounting.appcore.util.ConvertDatesToYears;
import org.shevliakov.collegeaccounting.database.repository.EmployeeRepository;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Employee;

public class EmployeeTabSubController {

  private final ChoiceBox<Rank> rankChoiceBox;
  private final ChoiceBox<Integer> birthYearChoiceBox;
  private final TextField nameSearchTextField;
  private final TableView<Employee> workersTableView;
  private final TableColumn<Employee, String> rankColumn;
  private final TableColumn<?, ?> fullNameColumn;
  private final TableColumn<?, ?> birthDateColumn;
  private final TableColumn<?, ?> registrationNumberColumn;
  private final TableColumn<?, ?> militarySpecialtyColumn;
  private final TableColumn<Employee, String> trainingColumn;
  private final TableColumn<?, ?> accountingCategoryColumn;
  private final TableColumn<?, ?> degreeColumn;
  private final TableColumn<?, ?> idInfoColumn;
  EmployeeRepository employeeRepository;
  private List<Employee> employees;
  private ObservableList<Employee> workersObservableList;

  public EmployeeTabSubController(ChoiceBox<Rank> rankChoiceBox,
      ChoiceBox<Integer> birthYearChoiceBox, TextField nameSearchTextField,
      TableView<Employee> workersTableView, TableColumn<Employee, String> rankColumn,
      TableColumn<?, ?> fullNameColumn, TableColumn<?, ?> birthDateColumn,
      TableColumn<?, ?> registrationNumberColumn, TableColumn<?, ?> militarySpecialtyColumn,
      TableColumn<Employee, String> trainingColumn, TableColumn<?, ?> accountingCategoryColumn,
      TableColumn<?, ?> degreeColumn, TableColumn<?, ?> idInfoColumn,
      EmployeeRepository employeeRepository) {
    this.rankChoiceBox = rankChoiceBox;
    this.birthYearChoiceBox = birthYearChoiceBox;
    this.nameSearchTextField = nameSearchTextField;
    this.workersTableView = workersTableView;
    this.rankColumn = rankColumn;
    this.fullNameColumn = fullNameColumn;
    this.birthDateColumn = birthDateColumn;
    this.registrationNumberColumn = registrationNumberColumn;
    this.militarySpecialtyColumn = militarySpecialtyColumn;
    this.trainingColumn = trainingColumn;
    this.accountingCategoryColumn = accountingCategoryColumn;
    this.degreeColumn = degreeColumn;
    this.idInfoColumn = idInfoColumn;
    this.employeeRepository = employeeRepository;
  }

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

  public void refreshData() {
    employees.clear();
    workersObservableList.clear();
    rankChoiceBox.getItems().clear();
    birthYearChoiceBox.getItems().clear();
    loadData();
    setupFiltering();
    workersTableView.refresh();
  }

  public void setupFiltering() {
    new SearchEmployeeByName().search(nameSearchTextField, employees, workersObservableList);
    new FilterEmployeesByBirthYear().filter(birthYearChoiceBox, rankChoiceBox, employees,
        workersObservableList);
    new FilterEmployeesByRank().filter(rankChoiceBox, birthYearChoiceBox, employees,
        workersObservableList);
    new EmployeeRowClickHandling().rowClickHandling(workersTableView);
  }

  public void setupTableColumns() {
    rankColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getRank().getName()));
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
    militarySpecialtyColumn.setCellValueFactory(new PropertyValueFactory<>("militarySpecialty"));
    trainingColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getTraining().getName()));
    accountingCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("accountingCategory"));
    degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
    idInfoColumn.setCellValueFactory(new PropertyValueFactory<>("idInfo"));
  }
}
