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
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.exception.GroupCanNotBeEmpty;
import org.shevliakov.collegeaccounting.exception.GroupExists;

public class GroupsTabSubController {

  private final TextField groupTextField;
  private final TableView<Group> groupsTableView;
  private final TableColumn<?, ?> groupTableColumn;
  private final TableColumn<Group, Button> deleteGroupTableColumn;
  private final GroupRepository groupRepository;
  private List<Group> groups;
  private ObservableList<Group> groupsObservableList;

  public GroupsTabSubController(TextField groupTextField, TableView<Group> groupsTableView,
      TableColumn<?, ?> groupTableColumn, TableColumn<Group, Button> deleteGroupTableColumn,
      GroupRepository groupRepository) {
    this.groupTextField = groupTextField;
    this.groupsTableView = groupsTableView;
    this.groupTableColumn = groupTableColumn;
    this.deleteGroupTableColumn = deleteGroupTableColumn;
    this.groupRepository = groupRepository;
  }

  public void loadData() {
    groups = groupRepository.getAll();
    groupsObservableList = groupsTableView.getItems();
    groupsObservableList.addAll(groups);
  }

  public void setupTableColumns() {
    groupTableColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
    setupDeleteGroupColumn();
  }

  private void setupDeleteGroupColumn() {
    Callback<TableColumn<Group, Button>, TableCell<Group, Button>> cellFactory = param -> new TableCell<>() {
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
            Group group = getTableView().getItems().get(getIndex());
            groupRepository.delete(group);
            refreshData();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    deleteGroupTableColumn.setCellFactory(cellFactory);
  }

  public void addGroup() {
    if (groupTextField.getText().isEmpty() || groupTextField.getText().isBlank()) {
      new GroupCanNotBeEmpty("Група не може бути порожньою").showAlert();
      return;
    } else if (groupRepository.existsByCode(groupTextField.getText())) {
      new GroupExists("Така група вже існує").showAlert();
      return;
    }
    Group group = new Group();
    group.setCode(groupTextField.getText());
    groupRepository.save(group);
    refreshData();
  }

  private void refreshData() {
    groups.clear();
    groupsObservableList.clear();
    loadData();
    groupsTableView.refresh();
  }
}
