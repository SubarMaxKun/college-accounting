package org.shevliakov.collegeaccounting.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.controller.filter.FilterStudentByGroup;
import org.shevliakov.collegeaccounting.controller.search.SearchStudentByName;
import org.shevliakov.collegeaccounting.database.repository.impl.GroupRepositoryImpl;
import org.shevliakov.collegeaccounting.database.repository.impl.StudentRepositoryImpl;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class MainController implements Initializable {

  @FXML
  private TextField nameTextField;
  @FXML
  private ChoiceBox<Date> yearChoiceBox;
  @FXML
  private ChoiceBox<Group> groupChoiceBox;
  @FXML
  private TableColumn<Student, String> fullNameColumn;
  @FXML
  private TableColumn<Student, Date> dateOfBirthColumn;
  @FXML
  private TableColumn<Student, String> addressColumn;
  @FXML
  private TableColumn<Student, String> groupColumn;
  @FXML
  private TableColumn<TextField, String> editColumn;
  @FXML
  private TableView<Student> studentsTableView;

  private List<Student> students;
  private ObservableList<Student> studentsObservableList;
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO: Потрібно додати фільтрування студентів по року народження із їхньої дати народження
    //  Для цього потрібно використати метод getYear() класу Date та метод getItems() класу yearChoiceBox

    blaBla();
    fillTableWithStudents();
    new FilterStudentByGroup().filter(groupChoiceBox, students, studentsObservableList);
    new SearchStudentByName().search(nameTextField, students, studentsObservableList);
  }

  private void fillTableWithStudents() {
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    groupColumn.setCellValueFactory(
        cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGroup().getCode()));
  }

  private void blaBla(){
    StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
    students = studentRepository.getAllStudents();
    studentsObservableList = studentsTableView.getItems();
    studentsObservableList.addAll(students);

    GroupRepositoryImpl groupRepository = new GroupRepositoryImpl();
    List<Group> groups = groupRepository.getAllGroups();
    ObservableList<Group> groupsObservableList = groupChoiceBox.getItems();
    groupsObservableList.add(null);
    groupsObservableList.addAll(groups);
  }
}
