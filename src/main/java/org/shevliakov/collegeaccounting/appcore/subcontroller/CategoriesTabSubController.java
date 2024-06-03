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
import org.hibernate.sql.ast.tree.expression.QueryLiteral;
import org.shevliakov.collegeaccounting.database.repository.QualificationCategoryRepository;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.shevliakov.collegeaccounting.entity.Rank;

public class CategoriesTabSubController {

  private final TableView<QualificationCategory> qualificationCategoriesTableView;
  private final TableColumn<?, ?> qualificationCategoryTableColumn;
  private final TableColumn<QualificationCategory, Button> deleteQualificationCategoryTableColumn;
  private final TextField qualificationCategoryTextField;
  private final QualificationCategoryRepository qualificationCategoryRepository;
  private List<QualificationCategory> qualificationCategories;
  private ObservableList<QualificationCategory> qualificationCategoriesObservableList;

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

  public void loadData() {
    qualificationCategories = qualificationCategoryRepository.getAll();
    qualificationCategoriesObservableList = qualificationCategoriesTableView.getItems();
    qualificationCategoriesObservableList.addAll(qualificationCategories);
  }

  public void setupTableColumns() {
    qualificationCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    setupDeleteQualificationCategoryColumn();
  }

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

  public void addQualificationCategory() {
    String name = qualificationCategoryTextField.getText();
    if (name.isEmpty()) {
      return;
    }
    QualificationCategory qualificationCategory = new QualificationCategory();
    qualificationCategory.setName(name);
    qualificationCategoryRepository.save(qualificationCategory);
    refreshData();
  }

  private void refreshData() {
    qualificationCategories.clear();
    qualificationCategoriesObservableList.clear();
    qualificationCategoryTextField.clear();
    loadData();
    qualificationCategoriesTableView.refresh();
  }
}
