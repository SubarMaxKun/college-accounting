package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.shevliakov.collegeaccounting.database.repository.QualificationCategoryRepository;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.shevliakov.collegeaccounting.exception.QualificationCategoryExistsException;

/**
 * Subcontroller for Categories tab.
 */
public class CategoriesTabSubController {

  private final TableView<QualificationCategory> qualificationCategoriesTableView;
  private final TableColumn<?, ?> qualificationCategoryTableColumn;
  private final TableColumn<QualificationCategory, Button> deleteQualificationCategoryTableColumn;
  private final TextField qualificationCategoryTextField;
  private final QualificationCategoryRepository qualificationCategoryRepository;
  private List<QualificationCategory> qualificationCategories;
  private ObservableList<QualificationCategory> qualificationCategoriesObservableList;

  /**
   * Constructor.
   *
   * @param qualificationCategoryTextField         TextField for entering qualification category
   *                                               name.
   * @param qualificationCategoriesTableView       TableView for displaying qualification
   *                                               categories.
   * @param qualificationCategoryTableColumn       TableColumn for displaying qualification category
   *                                               name.
   * @param deleteQualificationCategoryTableColumn TableColumn for deleting qualification category.
   * @param qualificationCategoryRepository        Repository for qualification categories.
   */
  public CategoriesTabSubController(TextField qualificationCategoryTextField,
      TableView<QualificationCategory> qualificationCategoriesTableView,
      TableColumn<?, ?> qualificationCategoryTableColumn,
      TableColumn<QualificationCategory, Button> deleteQualificationCategoryTableColumn,
      QualificationCategoryRepository qualificationCategoryRepository) {
    this.qualificationCategoryTextField = qualificationCategoryTextField;
    this.qualificationCategoriesTableView = qualificationCategoriesTableView;
    this.qualificationCategoryTableColumn = qualificationCategoryTableColumn;
    this.deleteQualificationCategoryTableColumn = deleteQualificationCategoryTableColumn;
    this.qualificationCategoryRepository = qualificationCategoryRepository;
  }

  /**
   * Loads data from the database and sets up the table columns.
   */
  public void loadData() {
    qualificationCategories = qualificationCategoryRepository.getAll();
    qualificationCategoriesObservableList = qualificationCategoriesTableView.getItems();
    qualificationCategoriesObservableList.addAll(qualificationCategories);
  }

  /**
   * Sets up the table columns.
   */
  public void setupTableColumns() {
    qualificationCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    setupDeleteQualificationCategoryColumn();
  }

  /**
   * Sets up the delete qualification category column.
   */
  private void setupDeleteQualificationCategoryColumn() {
    Callback<TableColumn<QualificationCategory, Button>, TableCell<QualificationCategory, Button>> cellFactory = param -> new TableCell<>() {
      final Button btn = new Button("Видалити");

      @Override
      public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
          setText(null);
        } else {
          Image image = new Image("org/shevliakov/collegeaccounting/images/delete.png");
          ImageView imageView = new ImageView(image);
          imageView.preserveRatioProperty().setValue(true);
          imageView.setFitHeight(15);
          btn.setGraphic(imageView);
          btn.setStyle("-fx-text-fill: red;");
          btn.setOnAction(event -> {
            QualificationCategory qualificationCategory = getTableView().getItems().get(getIndex());
            qualificationCategoryRepository.delete(qualificationCategory);
            refreshData();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    deleteQualificationCategoryTableColumn.setCellFactory(cellFactory);
  }

  /**
   * Adds a qualification category.
   */
  public void addQualificationCategory() {
    String name = qualificationCategoryTextField.getText();
    if (name.isEmpty() || name.isBlank()) {
      new FieldEmptyIllegalArgumentException("Кваліфікаційна категорія не можу бути пустою").showAlert();
      return;
    } else if (qualificationCategoryRepository.existsByName(name)) {
      new QualificationCategoryExistsException("Кваліфікаційна категорія з такою назвою вже існує").showAlert();
      return;
    }
    QualificationCategory qualificationCategory = new QualificationCategory();
    qualificationCategory.setName(name);
    qualificationCategoryRepository.save(qualificationCategory);
    refreshData();
  }

  /**
   * Refreshes the data in the table.
   */
  private void refreshData() {
    qualificationCategories.clear();
    qualificationCategoriesObservableList.clear();
    qualificationCategoryTextField.clear();
    loadData();
    qualificationCategoriesTableView.refresh();
  }
}
