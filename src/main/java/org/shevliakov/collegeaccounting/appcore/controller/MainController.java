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
import org.shevliakov.collegeaccounting.appcore.stage.EditWorkerInfoStage;
import org.shevliakov.collegeaccounting.appcore.subcontroller.StudentTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.WorkerTabSubController;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.database.repository.WorkerRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Student;
import org.shevliakov.collegeaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController implements Initializable {

  @FXML
  private ChoiceBox<Integer> workerBirthYearChoiceBox;
  @FXML
  private ChoiceBox<Rank> workerRankChoiceBox;
  @FXML
  private TextField workerNameTextField;
  @FXML
  private TableView<Worker> workersTableView;
  @FXML
  private TableColumn<Worker, String> workerRankColumn;
  @FXML
  private TableColumn<?, ?> workerFullNameColumn;
  @FXML
  private TableColumn<?, ?> workerBirthDateColumn;
  @FXML
  private TableColumn<?, ?> workerRegistrationNumberColumn;
  @FXML
  private TableColumn<?, ?> workerMilitarySpecialtyColumn;
  @FXML
  private TableColumn<Worker, String> workerTrainingColumn;
  @FXML
  private TableColumn<?, ?> workerAccountingCategoryColumn;
  @FXML
  private TableColumn<?, ?> workerDegreeColumn;
  @FXML
  private TableColumn<?, ?> workerIdInfoColumn;
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
  private WorkerTabSubController workerTabSubController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    studentTabSubController = new StudentTabSubController(studentBirthYearChoiceBox,
        studentGroupChoiceBox, studentNameTextField, studentsTableView, studentFullNameColumn,
        studentDateOfBirthColumn, studentAddressColumn, studentGroupColumn,
        context.getBean(StudentRepository.class), context.getBean(GroupRepository.class));
    setupStudentsTab();

    workerTabSubController = new WorkerTabSubController(workerRankChoiceBox, workerBirthYearChoiceBox,
        workerNameTextField, workersTableView, workerRankColumn, workerFullNameColumn,
        workerBirthDateColumn, workerRegistrationNumberColumn, workerMilitarySpecialtyColumn,
        workerTrainingColumn, workerAccountingCategoryColumn, workerDegreeColumn,
        workerIdInfoColumn, context.getBean(WorkerRepository.class));
    setupWorkersTab();
  }

  private void setupStudentsTab() {
    studentTabSubController.loadData();
    studentTabSubController.setupTableColumns();
    studentTabSubController.setupFiltering();
  }

  private void setupWorkersTab() {
    workerTabSubController.loadData();
    workerTabSubController.setupTableColumns();
    workerTabSubController.setupFiltering();
  }

  public void onRefreshWorkersButtonClicked() {
    workerTabSubController.refreshData();
  }

  public void onAddWorkerButtonClicked() {

    new EditWorkerInfoStage().open(Window.getWindows().getFirst(), null);
  }
}
