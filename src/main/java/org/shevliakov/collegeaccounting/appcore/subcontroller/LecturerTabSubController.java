package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.search.SearchPersonByName;
import org.shevliakov.collegeaccounting.appcore.util.LecturerRowClickHandling;
import org.shevliakov.collegeaccounting.database.repository.LecturerRepository;
import org.shevliakov.collegeaccounting.entity.Lecturer;

public class LecturerTabSubController {

  private final TextField lecturerNameTextField;
  private final TableView<Lecturer> lecturersTableView;
  private final TableColumn<?, ?> lecturerFullNameColumn1;
  private final TableColumn<?, ?> lecturerPosition;
  private final TableColumn<Lecturer, String> lecturerCategory;
  private final TableColumn<Lecturer, String> lecturerTitle;
  private final TableColumn<?, ?> lecturerLastCertification;
  private final TableColumn<?, ?> lecturerNextCertification;
  private final TableColumn<?, ?> lecturerHours;
  LecturerRepository lecturerRepository;
  private List<Lecturer> lecturers;
  private ObservableList<Lecturer> lecturersObservableList;

  public LecturerTabSubController(TextField lecturerNameTextField,
      TableView<Lecturer> lecturersTableView, TableColumn<?, ?> lecturerFullNameColumn1,
      TableColumn<?, ?> lecturerPosition, TableColumn<Lecturer, String> lecturerCategory,
      TableColumn<Lecturer, String> lecturerTitle, TableColumn<?, ?> lecturerLastCertification,
      TableColumn<?, ?> lecturerNextCertification, TableColumn<?, ?> lecturerHours,
      LecturerRepository lecturerRepository) {
    this.lecturerNameTextField = lecturerNameTextField;
    this.lecturersTableView = lecturersTableView;
    this.lecturerFullNameColumn1 = lecturerFullNameColumn1;
    this.lecturerPosition = lecturerPosition;
    this.lecturerCategory = lecturerCategory;
    this.lecturerTitle = lecturerTitle;
    this.lecturerLastCertification = lecturerLastCertification;
    this.lecturerNextCertification = lecturerNextCertification;
    this.lecturerHours = lecturerHours;
    this.lecturerRepository = lecturerRepository;
  }

  public void loadData() {
    lecturers = lecturerRepository.getAll();
    lecturersObservableList = lecturersTableView.getItems();
    lecturersObservableList.addAll(lecturers);
  }

  public void setupTableColumns() {
    lecturerFullNameColumn1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    lecturerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
    lecturerCategory.setCellValueFactory(lecturerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        lecturerStringCellDataFeatures.getValue().getCategory().getName()));
    lecturerTitle.setCellValueFactory(lecturerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        lecturerStringCellDataFeatures.getValue().getTitle().getName()));
    lecturerLastCertification.setCellValueFactory(new PropertyValueFactory<>("lastCertification"));
    lecturerNextCertification.setCellValueFactory(new PropertyValueFactory<>("nextCertification"));
    lecturerHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
    new LecturerRowClickHandling().rowClickHandling(lecturersTableView,
        LecturerTabSubController.this);
  }

  public void setupFiltering() {
    new SearchPersonByName().search(lecturerNameTextField, lecturers, lecturersObservableList);
  }

  public void refreshData() {
    lecturers.clear();
    lecturersObservableList.clear();
    lecturerNameTextField.clear();
    loadData();
    setupFiltering();
    lecturersTableView.refresh();
  }

  public void updateNextCertification() {
    for (Lecturer lecturer : lecturers) {
      lecturer.setLastCertification(lecturer.getNextCertification());
      Integer nextCertificationYear = lecturer.getNextCertification() + 5;
      lecturer.setNextCertification(nextCertificationYear);
      lecturerRepository.save(lecturer);
    }
    refreshData();
  }
}
