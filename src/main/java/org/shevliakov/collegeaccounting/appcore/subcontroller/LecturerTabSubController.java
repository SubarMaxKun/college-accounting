package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterLecturersByCategory;
import org.shevliakov.collegeaccounting.appcore.filter.FilterLecturersByNextCertification;
import org.shevliakov.collegeaccounting.appcore.filter.FilterLecturersByTitle;
import org.shevliakov.collegeaccounting.appcore.search.SearchPersonByName;
import org.shevliakov.collegeaccounting.appcore.util.LecturerRowClickHandling;
import org.shevliakov.collegeaccounting.database.repository.LecturerRepository;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;

/**
 * Subcontroller for Lecturer tab.
 */
public class LecturerTabSubController {

  private final ChoiceBox<QualificationCategory> lecturerCategoryChoiceBox;
  private final ChoiceBox<PedagogicalTitle> lecturerTitleChoiceBox;
  private final ChoiceBox<Integer> lecturerNextCertificationChoiceBox;
  private final TextField lecturerNameTextField;
  private final TableView<Lecturer> lecturersTableView;
  private final TableColumn<?, ?> lecturerFullNameColumn1;
  private final TableColumn<?, ?> lecturerPosition;
  private final TableColumn<Lecturer, String> lecturerCategory;
  private final TableColumn<Lecturer, String> lecturerTitle;
  private final TableColumn<?, ?> lecturerLastCertification;
  private final TableColumn<?, ?> lecturerNextCertification;
  private final TableColumn<?, ?> lecturerHours;
  private final TableColumn<?, ?> lecturerCertificate;
  private final LecturerRepository lecturerRepository;
  private List<Lecturer> lecturers;
  private ObservableList<Lecturer> lecturersObservableList;

  /**
   * Constructor for LecturerTabSubController.
   *
   * @param lecturerCategoryChoiceBox          ChoiceBox for filtering by qualification category.
   * @param lecturerTitleChoiceBox             ChoiceBox for filtering by pedagogical title.
   * @param lecturerNextCertificationChoiceBox ChoiceBox for filtering by next certification year.
   * @param lecturerNameTextField              TextField for searching by name.
   * @param lecturersTableView                 TableView for displaying lecturers.
   * @param lecturerFullNameColumn1            TableColumn for displaying full name of lecturer.
   * @param lecturerPosition                   TableColumn for displaying position of lecturer.
   * @param lecturerCategory                   TableColumn for displaying qualification category of
   *                                           lecturer.
   * @param lecturerTitle                      TableColumn for displaying pedagogical title of
   *                                           lecturer.
   * @param lecturerLastCertification          TableColumn for displaying last certification year of
   *                                           lecturer.
   * @param lecturerNextCertification          TableColumn for displaying next certification year of
   *                                           lecturer.
   * @param lecturerHours                      TableColumn for displaying hours of lecturer.
   * @param lecturerCertificate                TableColumn for displaying certificate of lecturer.
   * @param lecturerRepository                 Repository for lecturer entity.
   */
  public LecturerTabSubController(ChoiceBox<QualificationCategory> lecturerCategoryChoiceBox,
      ChoiceBox<PedagogicalTitle> lecturerTitleChoiceBox,
      ChoiceBox<Integer> lecturerNextCertificationChoiceBox, TextField lecturerNameTextField,
      TableView<Lecturer> lecturersTableView, TableColumn<?, ?> lecturerFullNameColumn1,
      TableColumn<?, ?> lecturerPosition, TableColumn<Lecturer, String> lecturerCategory,
      TableColumn<Lecturer, String> lecturerTitle, TableColumn<?, ?> lecturerLastCertification,
      TableColumn<?, ?> lecturerNextCertification, TableColumn<?, ?> lecturerHours,
      TableColumn<?, ?> lecturerCertificate, LecturerRepository lecturerRepository) {
    this.lecturerCategoryChoiceBox = lecturerCategoryChoiceBox;
    this.lecturerTitleChoiceBox = lecturerTitleChoiceBox;
    this.lecturerNextCertificationChoiceBox = lecturerNextCertificationChoiceBox;
    this.lecturerNameTextField = lecturerNameTextField;
    this.lecturersTableView = lecturersTableView;
    this.lecturerFullNameColumn1 = lecturerFullNameColumn1;
    this.lecturerPosition = lecturerPosition;
    this.lecturerCategory = lecturerCategory;
    this.lecturerTitle = lecturerTitle;
    this.lecturerLastCertification = lecturerLastCertification;
    this.lecturerNextCertification = lecturerNextCertification;
    this.lecturerHours = lecturerHours;
    this.lecturerCertificate = lecturerCertificate;
    this.lecturerRepository = lecturerRepository;
  }

  /**
   * Load data to TableView and ChoiceBoxes.
   */
  public void loadData() {
    lecturers = lecturerRepository.getAll();
    lecturersObservableList = lecturersTableView.getItems();
    lecturersObservableList.addAll(lecturers);

    List<QualificationCategory> qualificationCategories = lecturerRepository.getDistinctQualificationCategories();
    lecturerCategoryChoiceBox.getItems().add(null);
    lecturerCategoryChoiceBox.getItems().addAll(qualificationCategories);

    List<PedagogicalTitle> pedagogicalTitles = lecturerRepository.getDistinctPedagogicalTitles();
    lecturerTitleChoiceBox.getItems().add(null);
    lecturerTitleChoiceBox.getItems().addAll(pedagogicalTitles);

    List<Integer> nextCertificationYears = lecturerRepository.getDistinctNextCertification();
    nextCertificationYears.sort(null);
    lecturerNextCertificationChoiceBox.getItems().add(null);
    lecturerNextCertificationChoiceBox.getItems().addAll(nextCertificationYears);
  }

  /**
   * Setup columns for TableView.
   */
  public void setupTableColumns() {
    lecturerFullNameColumn1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    lecturerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
    lecturerCategory.setCellValueFactory(
        lecturerStringCellDataFeatures -> new ReadOnlyStringWrapper(
            lecturerStringCellDataFeatures.getValue().getCategory().getName()));
    lecturerTitle.setCellValueFactory(lecturerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        lecturerStringCellDataFeatures.getValue().getTitle().getName()));
    lecturerLastCertification.setCellValueFactory(new PropertyValueFactory<>("lastCertification"));
    lecturerNextCertification.setCellValueFactory(new PropertyValueFactory<>("nextCertification"));
    lecturerHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
    lecturerCertificate.setCellValueFactory(new PropertyValueFactory<>("certificate"));
    new LecturerRowClickHandling().rowClickHandling(lecturersTableView,
        LecturerTabSubController.this);
  }

  /**
   * Setup filtering for TableView.
   */
  public void setupFiltering() {
    new FilterLecturersByCategory().filter(lecturerCategoryChoiceBox, lecturerTitleChoiceBox,
        lecturerNextCertificationChoiceBox, lecturerNameTextField, lecturers,
        lecturersObservableList);
    new FilterLecturersByTitle().filter(lecturerTitleChoiceBox, lecturerCategoryChoiceBox,
        lecturerNextCertificationChoiceBox, lecturerNameTextField, lecturers,
        lecturersObservableList);
    new FilterLecturersByNextCertification().filter(lecturerNextCertificationChoiceBox,
        lecturerTitleChoiceBox, lecturerCategoryChoiceBox, lecturerNameTextField, lecturers,
        lecturersObservableList);
    new SearchPersonByName().search(lecturerNameTextField, lecturers, lecturersObservableList);
  }

  /**
   * Refresh data in TableView.
   */
  public void refreshData() {
    lecturers.clear();
    lecturersObservableList.clear();
    lecturerNameTextField.clear();
    lecturerCategoryChoiceBox.getItems().clear();
    lecturerTitleChoiceBox.getItems().clear();
    lecturerNextCertificationChoiceBox.getItems().clear();
    lecturerNameTextField.clear();
    loadData();
    setupFiltering();
    lecturersTableView.refresh();
  }

  /**
   * Update next certification year for all lecturers.
   */
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
