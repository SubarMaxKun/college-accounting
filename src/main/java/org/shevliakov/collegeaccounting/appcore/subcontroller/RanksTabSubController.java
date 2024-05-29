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
import org.shevliakov.collegeaccounting.exception.RankCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.RankExists;

public class RanksTabSubController {

  private final TextField rankTextField;
  private final TableView<Rank> ranksTableView;
  private final TableColumn<?, ?> rankTableColumn;
  private final TableColumn<Rank, Button> deleteRankTableColumn;
  private final RankRepository rankRepository;
  private List<Rank> ranks;
  private ObservableList<Rank> ranksObservableList;

  public RanksTabSubController(TextField rankTextField, TableView<Rank> ranksTableView,
      TableColumn<?, ?> rankTableColumn, TableColumn<Rank, Button> deleteRankTableColumn,
      RankRepository rankRepository) {
    this.rankTextField = rankTextField;
    this.ranksTableView = ranksTableView;
    this.rankTableColumn = rankTableColumn;
    this.deleteRankTableColumn = deleteRankTableColumn;
    this.rankRepository = rankRepository;
  }

  public void loadData() {
    ranks = rankRepository.getAll();
    ranksObservableList = ranksTableView.getItems();
    ranksObservableList.addAll(ranks);
  }

  public void setupTableColumns() {
    rankTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    setupDeleteRankColumn();
  }

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

  public void addRank() {
    if (rankTextField.getText().isEmpty() || rankTextField.getText().isBlank()) {
      new RankCanNotBeEmpty("Звання не може бути порожнім").showAlert();
      return;
    } else if (rankRepository.existsByName(rankTextField.getText())) {
      new RankExists("Таке звання вже існує").showAlert();
      return;
    }
    Rank rank = new Rank();
    rank.setName(rankTextField.getText());
    rankRepository.save(rank);
    refreshData();
  }

  private void refreshData() {
    ranks.clear();
    ranksObservableList.clear();
    loadData();
    ranksTableView.refresh();
  }
}
