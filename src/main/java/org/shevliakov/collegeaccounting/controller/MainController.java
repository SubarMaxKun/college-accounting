package org.shevliakov.collegeaccounting.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.controller.filter.FilterStudentsByGroup;
import org.shevliakov.collegeaccounting.controller.filter.FilterStudentsByYearOfBirth;
import org.shevliakov.collegeaccounting.controller.search.SearchStudentByName;
import org.shevliakov.collegeaccounting.controller.util.StudentRowClickHandling;
import org.shevliakov.collegeaccounting.controller.util.ConvertDatesToYears;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController implements Initializable {

  @FXML
  private TextField nameTextField;
  @FXML
  private ChoiceBox<Integer> yearChoiceBox;
  @FXML
  private ChoiceBox<Group> groupChoiceBox;
  @FXML
  private TableColumn<Student, String> studentFullNameColumn;
  @FXML
  private TableColumn<Student, Date> dateOfBirthColumn;
  @FXML
  private TableColumn<Student, String> addressColumn;
  @FXML
  private TableColumn<Student, String> groupColumn;
  @FXML
  private TableColumn<Student, Button> editColumn;
  @FXML
  private TableView<Student> studentsTableView;

  private List<Student> students;
  private ObservableList<Student> studentsObservableList;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO: Потрібно додати фільтрування студентів по року народження із їхньої дати народження
    //  Для цього потрібно використати метод getYear() класу Date та метод getItems() класу yearChoiceBox

    retrieveData();
    fillTableWithStudents();
    new FilterStudentsByGroup().filter(groupChoiceBox, yearChoiceBox, students, studentsObservableList);
    new SearchStudentByName().search(nameTextField, students, studentsObservableList);
    new FilterStudentsByYearOfBirth().filter(yearChoiceBox, groupChoiceBox, students, studentsObservableList);
    new StudentRowClickHandling().rowClickHandling(studentsTableView);
  }

  private void fillTableWithStudents() {
    studentFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    groupColumn.setCellValueFactory(
        cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGroup().getCode()));
  }

  private void retrieveData() {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    StudentRepository studentRepository = context.getBean(StudentRepository.class);
    students = studentRepository.getAll();
    studentsObservableList = studentsTableView.getItems();
    studentsObservableList.addAll(students);

    List<Integer> years = ConvertDatesToYears.convert(studentRepository.getDistinctBirthDates());
    yearChoiceBox.getItems().add(null);
    yearChoiceBox.getItems().addAll(years);

    GroupRepository groupRepository = context.getBean(GroupRepository.class);
    List<Group> groups = groupRepository.getAll();
    ObservableList<Group> groupsObservableList = groupChoiceBox.getItems();
    groupsObservableList.add(null);
    groupsObservableList.addAll(groups);

  }

  public void onRefreshWorkersButtonClicked(ActionEvent actionEvent) {
  }

  public void onAddWorkerButtonClicked(ActionEvent actionEvent) {
  }
}
