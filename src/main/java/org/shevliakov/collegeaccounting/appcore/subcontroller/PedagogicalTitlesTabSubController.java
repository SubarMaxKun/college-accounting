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
import org.shevliakov.collegeaccounting.database.repository.PedagogicalTitleRepository;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;

/**
 * Subcontroller for PedagogicalTitlesTab.
 */
public class PedagogicalTitlesTabSubController {

  private final TextField pedagogicalTitleTextField;
  private final TableView<PedagogicalTitle> pedagogicalTitlesTableView;
  private final TableColumn<?, ?> pedagogicalTitleTableColumn;
  private final TableColumn<PedagogicalTitle, Button> deletePedagogicalTitleTableColumn;
  private final PedagogicalTitleRepository pedagogicalTitleRepository;
  private List<PedagogicalTitle> pedagogicalTitles;
  private ObservableList<PedagogicalTitle> pedagogicalTitlesObservableList;

  /**
   * Constructor.
   *
   * @param pedagogicalTitleTextField         TextField for entering pedagogical title name.
   * @param pedagogicalTitlesTableView        TableView for displaying pedagogical titles.
   * @param pedagogicalTitleTableColumn       TableColumn for displaying pedagogical title name.
   * @param deletePedagogicalTitleTableColumn TableColumn for deleting pedagogical title.
   * @param pedagogicalTitleRepository        Repository for PedagogicalTitle entity.
   */
  public PedagogicalTitlesTabSubController(TextField pedagogicalTitleTextField,
      TableView<PedagogicalTitle> pedagogicalTitlesTableView,
      TableColumn<?, ?> pedagogicalTitleTableColumn,
      TableColumn<PedagogicalTitle, Button> deletePedagogicalTitleTableColumn,
      PedagogicalTitleRepository pedagogicalTitleRepository) {
    this.pedagogicalTitleTextField = pedagogicalTitleTextField;
    this.pedagogicalTitlesTableView = pedagogicalTitlesTableView;
    this.pedagogicalTitleTableColumn = pedagogicalTitleTableColumn;
    this.deletePedagogicalTitleTableColumn = deletePedagogicalTitleTableColumn;
    this.pedagogicalTitleRepository = pedagogicalTitleRepository;
  }

  /**
   * Load data from database and display it in TableView.
   */
  public void loadData() {
    pedagogicalTitles = pedagogicalTitleRepository.findAll();
    pedagogicalTitlesObservableList = pedagogicalTitlesTableView.getItems();
    pedagogicalTitlesObservableList.clear();
    pedagogicalTitlesObservableList.addAll(pedagogicalTitles);
  }

  /**
   * Setup columns for TableView.
   */
  public void setupTableColumns() {
    pedagogicalTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    setupDeletePedagogicalTitleColumn();
  }

  /**
   * Setup column for deleting pedagogical title.
   */
  private void setupDeletePedagogicalTitleColumn() {
    Callback<TableColumn<PedagogicalTitle, Button>, TableCell<PedagogicalTitle, Button>> cellFactory = param -> new TableCell<>() {
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
            PedagogicalTitle pedagogicalTitle = getTableView().getItems().get(getIndex());
            pedagogicalTitleRepository.delete(pedagogicalTitle);
            refreshData();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    deletePedagogicalTitleTableColumn.setCellFactory(cellFactory);
  }

  /**
   * Add new pedagogical title to database.
   */
  public void addPedagogicalTitle() {
    String pedagogicalTitleName = pedagogicalTitleTextField.getText();
    if (pedagogicalTitleName.isEmpty()) {
      return;
    }
    PedagogicalTitle pedagogicalTitle = new PedagogicalTitle();
    pedagogicalTitle.setName(pedagogicalTitleName);
    pedagogicalTitleRepository.save(pedagogicalTitle);
    refreshData();
  }

  /**
   * Refresh data in TableView.
   */
  private void refreshData() {
    pedagogicalTitles.clear();
    pedagogicalTitlesObservableList.clear();
    pedagogicalTitleTextField.clear();
    loadData();
    pedagogicalTitlesTableView.refresh();
  }
}
