package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterStudentsByGroup;
import org.shevliakov.collegeaccounting.appcore.filter.FilterStudentsByYearOfBirth;
import org.shevliakov.collegeaccounting.appcore.search.SearchStudentByName;
import org.shevliakov.collegeaccounting.appcore.util.ConvertDatesToYears;
import org.shevliakov.collegeaccounting.appcore.util.StudentRowClickHandling;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class StudentTabSubController {

  private final TextField nameTextField;
  private final ChoiceBox<Integer> yearChoiceBox;
  private final ChoiceBox<Group> groupChoiceBox;
  private final TableView<Student> studentsTableView;
  private final TableColumn<?, ?> studentFullNameColumn;
  private final TableColumn<?, ?> dateOfBirthColumn;
  private final TableColumn<?, ?> addressColumn;
  private final TableColumn<Student, String> groupColumn;
  private final StudentRepository studentRepository;
  private final GroupRepository groupRepository;
  private List<Student> students;
  private ObservableList<Student> studentsObservableList;

  public StudentTabSubController(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Group> groupChoiceBox,
      TextField nameTextField, TableView<Student> studentsTableView,
      TableColumn<?, ?> studentFullNameColumn,
      TableColumn<?, ?> dateOfBirthColumn, TableColumn<?, ?> addressColumn,
      TableColumn<Student, String> groupColumn, StudentRepository studentRepository,
      GroupRepository groupRepository) {
    this.yearChoiceBox = yearChoiceBox;
    this.groupChoiceBox = groupChoiceBox;
    this.nameTextField = nameTextField;
    this.studentsTableView = studentsTableView;
    this.studentFullNameColumn = studentFullNameColumn;
    this.dateOfBirthColumn = dateOfBirthColumn;
    this.addressColumn = addressColumn;
    this.groupColumn = groupColumn;
    this.studentRepository = studentRepository;
    this.groupRepository = groupRepository;
  }

  public void loadData() {
    students = studentRepository.getAll();
    studentsObservableList = studentsTableView.getItems();
    studentsObservableList.addAll(students);

    List<Integer> years = ConvertDatesToYears.convert(studentRepository.getDistinctBirthDates());
    yearChoiceBox.getItems().add(null);
    yearChoiceBox.getItems().addAll(years);

    List<Group> groups = groupRepository.getAll();
    ObservableList<Group> groupsObservableList = groupChoiceBox.getItems();
    groupsObservableList.add(null);
    groupsObservableList.addAll(groups);
  }

  public void setupTableColumns() {
    studentFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    groupColumn.setCellValueFactory(
        cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGroup().getCode()));
  }

  public void setupFiltering() {
    new FilterStudentsByGroup().filter(groupChoiceBox, yearChoiceBox, nameTextField, students,
        studentsObservableList);
    new FilterStudentsByYearOfBirth().filter(yearChoiceBox, groupChoiceBox, nameTextField, students,
        studentsObservableList);
    new SearchStudentByName().search(nameTextField, students, studentsObservableList);
    new StudentRowClickHandling().rowClickHandling(studentsTableView, StudentTabSubController.this);
  }

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
