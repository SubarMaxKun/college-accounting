package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterStudentsByGroup;
import org.shevliakov.collegeaccounting.appcore.filter.FilterStudentsByYearOfBirth;
import org.shevliakov.collegeaccounting.appcore.search.SearchPersonByName;
import org.shevliakov.collegeaccounting.appcore.util.ConvertDatesToYears;
import org.shevliakov.collegeaccounting.appcore.util.StudentRowClickHandling;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

/**
 * Subcontroller for Student tab.
 */
public class StudentTabSubController {

  private final TextField nameTextField;
  private final ChoiceBox<Integer> yearChoiceBox;
  private final ChoiceBox<Group> groupChoiceBox;
  private final TableView<Student> studentsTableView;
  private final TableColumn<?, ?> studentFullNameColumn;
  private final TableColumn<?, ?> dateOfBirthColumn;
  private final TableColumn<?, ?> addressColumn;
  private final TableColumn<Student, String> groupColumn;
  private final TableColumn<Student, Boolean> studentOnTckColumn;
  private final TableColumn<?, ?> studentMilitaryDocumentColumn;
  private final TableColumn<?, ?> studentSpecialtyAndRankColumn;
  private final TableColumn<?, ?> studentTckNameColumn;
  private final TableColumn<?, ?> studentTaxCardColumn;
  private final TableColumn<?, ?> studentNotesColumn;
  private final StudentRepository studentRepository;
  private List<Student> students;
  private ObservableList<Student> studentsObservableList;

  /**
   * Constructor.
   *
   * @param yearChoiceBox         ChoiceBox for filtering students by year of birth.
   * @param groupChoiceBox        ChoiceBox for filtering students by group.
   * @param nameTextField         TextField for searching students by name.
   * @param studentsTableView     TableView for displaying students.
   * @param studentFullNameColumn TableColumn for displaying student's full name.
   * @param dateOfBirthColumn     TableColumn for displaying student's date of birth.
   * @param addressColumn         TableColumn for displaying student's address.
   * @param groupColumn           TableColumn for displaying student's group.
   * @param studentOnTckColumn    TableColumn for displaying student's TCK status.
   * @param studentNotesColumn    TableColumn for displaying student's notes.
   * @param studentRepository     StudentRepository for accessing student data.
   */
  public StudentTabSubController(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Group> groupChoiceBox,
      TextField nameTextField, TableView<Student> studentsTableView,
      TableColumn<?, ?> studentFullNameColumn, TableColumn<?, ?> dateOfBirthColumn,
      TableColumn<?, ?> addressColumn, TableColumn<Student, String> groupColumn,
      TableColumn<Student, Boolean> studentOnTckColumn,
      TableColumn<?, ?> studentMilitaryDocumentColumn,
      TableColumn<?, ?> studentSpecialtyAndRankColumn, TableColumn<?, ?> studentTckNameColumn,
      TableColumn<?, ?> studentTaxCardColumn, TableColumn<?, ?> studentNotesColumn,
      StudentRepository studentRepository) {
    this.yearChoiceBox = yearChoiceBox;
    this.groupChoiceBox = groupChoiceBox;
    this.nameTextField = nameTextField;
    this.studentsTableView = studentsTableView;
    this.studentFullNameColumn = studentFullNameColumn;
    this.dateOfBirthColumn = dateOfBirthColumn;
    this.addressColumn = addressColumn;
    this.groupColumn = groupColumn;
    this.studentOnTckColumn = studentOnTckColumn;
    this.studentMilitaryDocumentColumn = studentMilitaryDocumentColumn;
    this.studentSpecialtyAndRankColumn = studentSpecialtyAndRankColumn;
    this.studentTckNameColumn = studentTckNameColumn;
    this.studentTaxCardColumn = studentTaxCardColumn;
    this.studentNotesColumn = studentNotesColumn;
    this.studentRepository = studentRepository;
  }

  /**
   * Loads data into the TableView and ChoiceBoxes.
   */
  public void loadData() {
    students = studentRepository.getAll();
    studentsObservableList = studentsTableView.getItems();
    studentsObservableList.addAll(students);

    List<Integer> years = ConvertDatesToYears.convert(studentRepository.getDistinctBirthDates());
    yearChoiceBox.getItems().add(null);
    yearChoiceBox.getItems().addAll(years);

    List<Group> groups = studentRepository.getDistinctGroups();
    ObservableList<Group> groupsObservableList = groupChoiceBox.getItems();
    groupsObservableList.add(null);
    groupsObservableList.addAll(groups);
  }

  /**
   * Sets up columns in the TableView.
   */
  public void setupTableColumns() {
    studentFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    groupColumn.setCellValueFactory(
        cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGroup().getCode()));
    studentOnTckColumn.setCellValueFactory(
        cellData -> new SimpleBooleanProperty(cellData.getValue().getOnTck()));
    studentOnTckColumn.setCellFactory(column -> new CheckBoxTableCell<>());
    studentMilitaryDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("militaryDocument"));
    studentSpecialtyAndRankColumn.setCellValueFactory(new PropertyValueFactory<>("specialtyAndRank"));
    studentTckNameColumn.setCellValueFactory(new PropertyValueFactory<>("tckName"));
    studentTaxCardColumn.setCellValueFactory(new PropertyValueFactory<>("taxCardNumber"));
    studentNotesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
  }

  /**
   * Sets up filtering for the TableView.
   */
  public void setupFiltering() {
    new FilterStudentsByGroup().filter(groupChoiceBox, yearChoiceBox, nameTextField, students,
        studentsObservableList);
    new FilterStudentsByYearOfBirth().filter(yearChoiceBox, groupChoiceBox, nameTextField, students,
        studentsObservableList);
    new SearchPersonByName().search(nameTextField, students, studentsObservableList);
    new StudentRowClickHandling().rowClickHandling(studentsTableView, StudentTabSubController.this);
  }

  /**
   * Refreshes data in the TableView and ChoiceBoxes.
   */
  public void refreshData() {
    students.clear();
    studentsObservableList.clear();
    yearChoiceBox.getItems().clear();
    groupChoiceBox.getItems().clear();
    nameTextField.clear();
    loadData();
    setupFiltering();
    studentsTableView.refresh();
  }
}
