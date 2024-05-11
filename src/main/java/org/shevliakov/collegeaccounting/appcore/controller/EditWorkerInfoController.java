package org.shevliakov.collegeaccounting.appcore.controller;

import java.sql.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.RankRepository;
import org.shevliakov.collegeaccounting.database.repository.TrainingRepository;
import org.shevliakov.collegeaccounting.database.repository.WorkerRepository;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Training;
import org.shevliakov.collegeaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EditWorkerInfoController {

  @FXML
  private TextField fullNameTextField;
  @FXML
  private ChoiceBox<String> rankChoiceBox;
  @FXML
  private DatePicker birthDateDatePicker;
  @FXML
  private TextField registrationNumberTextField;
  @FXML
  private TextField militarySpecialtyTextField;
  @FXML
  private ChoiceBox<String> trainingChoiceBox;
  @FXML
  private TextField accountingCategoryTextField;
  @FXML
  private TextField degreeTextField;
  @FXML
  private TextArea idInfoTextArea;
  @FXML
  private Button cancelButton;
  @FXML
  private Button commitButton;
  @FXML
  private Button deleteButton;
  private Worker worker;
  private WorkerRepository workerRepository;
  private RankRepository rankRepository;
  private TrainingRepository trainingRepository;

  public void initialize(Worker worker) {
    if (worker != null) {
      this.worker = worker;
      setWorkerInfo();
    } else {
      commitButton.setText("Додати");
      deleteButton.setVisible(false);
    }

    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    workerRepository = context.getBean(WorkerRepository.class);
    rankRepository = context.getBean(RankRepository.class);
    trainingRepository = context.getBean(TrainingRepository.class);

    List<Rank> ranks = rankRepository.findAll();
    for (Rank rank : ranks) {
      rankChoiceBox.getItems().add(rank.getName());
    }

    List<Training> trainings = trainingRepository.findAll();
    for (Training training : trainings) {
      trainingChoiceBox.getItems().add(training.getName());
    }
  }

  private void setWorkerInfo() {
    fullNameTextField.setText(worker.getFullName());
    rankChoiceBox.setValue(worker.getRank().getName());
    birthDateDatePicker.setValue(worker.getBirthDate().toLocalDate());
    registrationNumberTextField.setText(worker.getRegistrationNumber());
    militarySpecialtyTextField.setText(worker.getMilitarySpecialty());
    trainingChoiceBox.setValue(worker.getTraining().getName());
    accountingCategoryTextField.setText(worker.getAccountingCategory());
    degreeTextField.setText(worker.getDegree());
    idInfoTextArea.setText(worker.getIdInfo());
  }

  @FXML
  private void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void onCommitButtonClicked() {
    Worker workerToPersist = new Worker();
    workerToPersist.setFullName(fullNameTextField.getText());
    workerToPersist.setRank(rankRepository.getByName(rankChoiceBox.getValue()));
    workerToPersist.setBirthDate(Date.valueOf(birthDateDatePicker.getValue()));
    workerToPersist.setRegistrationNumber(registrationNumberTextField.getText());
    workerToPersist.setMilitarySpecialty(militarySpecialtyTextField.getText());
    workerToPersist.setTraining(trainingRepository.getByName(trainingChoiceBox.getValue()));
    workerToPersist.setAccountingCategory(accountingCategoryTextField.getText());
    workerToPersist.setDegree(degreeTextField.getText());
    workerToPersist.setIdInfo(idInfoTextArea.getText());
    workerRepository.save(workerToPersist);
    commitButton.getScene().getWindow().hide();
  }

  @FXML
  private void onDeleteButtonClicked() {
    workerRepository.delete(worker);
    deleteButton.getScene().getWindow().hide();
  }
}
