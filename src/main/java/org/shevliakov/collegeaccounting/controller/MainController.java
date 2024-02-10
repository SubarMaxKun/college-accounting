package org.shevliakov.collegeaccounting.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.database.repository.impl.StudentRepositoryImpl;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public class MainController implements Initializable {

  @FXML
  private TableColumn<Student, String> fullNameColumn;
  @FXML
  private TableColumn<Student, Date> dateOfBirthColumn;
  @FXML
  private TableColumn<Student, String> addressColumn;
  @FXML
  private TableColumn<Student, String> groupColumn;
  @FXML
  private TableColumn editColumn;
  @FXML
  private TableView<Student> studentsTableView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // TODO: потрібно провести рефакторинг коду, винести його в окремий клас та зробити його більш читабельним
    //  За цим посиланням можна знайти алтернативу для 49го рядка
    //  https://stackoverflow.com/questions/30334450/cannot-convert-from-string-to-observablevaluestring

    StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
    List<Student> students = studentRepository.getAllStudents();
    ObservableList<Student> studentsObservableList = studentsTableView.getItems();
    studentsObservableList.addAll(students);

    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    groupColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGroup().getCode()));

  }
}
