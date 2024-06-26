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
import org.shevliakov.collegeaccounting.database.repository.RankRepository;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.shevliakov.collegeaccounting.exception.RankExistsException;

/**
 * Subcontroller for RanksTabController.
 */
public class RanksTabSubController {

  private final TextField rankTextField;
  private final TableView<Rank> ranksTableView;
  private final TableColumn<?, ?> rankTableColumn;
  private final TableColumn<Rank, Button> deleteRankTableColumn;
  private final RankRepository rankRepository;
  private List<Rank> ranks;
  private ObservableList<Rank> ranksObservableList;

  /**
   * Constructor.
   *
   * @param rankTextField         TextField for rank name input.
   * @param ranksTableView        TableView for ranks.
   * @param rankTableColumn       TableColumn for rank name.
   * @param deleteRankTableColumn TableColumn for delete button.
   * @param rankRepository        RankRepository.
   */
  public RanksTabSubController(TextField rankTextField, TableView<Rank> ranksTableView,
      TableColumn<?, ?> rankTableColumn, TableColumn<Rank, Button> deleteRankTableColumn,
      RankRepository rankRepository) {
    this.rankTextField = rankTextField;
    this.ranksTableView = ranksTableView;
    this.rankTableColumn = rankTableColumn;
    this.deleteRankTableColumn = deleteRankTableColumn;
    this.rankRepository = rankRepository;
  }

  /**
   * Load data from database and set it to TableView.
   */
  public void loadData() {
    ranks = rankRepository.getAll();
    ranksObservableList = ranksTableView.getItems();
    ranksObservableList.addAll(ranks);
  }

  /**
   * Setup columns for TableView.
   */
  public void setupTableColumns() {
    rankTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    setupDeleteRankColumn();
  }

  /**
   * Setup delete button column.
   */
  private void setupDeleteRankColumn() {
    Callback<TableColumn<Rank, Button>, TableCell<Rank, Button>> cellFactory = param -> new TableCell<>() {
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
            Rank rank = getTableView().getItems().get(getIndex());
            rankRepository.delete(rank);
            refreshData();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    deleteRankTableColumn.setCellFactory(cellFactory);
  }

  /**
   * Add rank to database.
   */
  public void addRank() {
    if (rankTextField.getText().isEmpty() || rankTextField.getText().isBlank()) {
      new FieldEmptyIllegalArgumentException("Звання не може бути порожнім").showAlert();
      return;
    } else if (rankRepository.existsByName(rankTextField.getText())) {
      new RankExistsException("Таке звання вже існує").showAlert();
      return;
    }
    Rank rank = new Rank();
    rank.setName(rankTextField.getText());
    rankRepository.save(rank);
    refreshData();
  }

  /**
   * Refresh data in TableView.
   */
  private void refreshData() {
    ranks.clear();
    ranksObservableList.clear();
    rankTextField.clear();
    loadData();
    ranksTableView.refresh();
  }
}
