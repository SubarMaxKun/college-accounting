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
import org.shevliakov.collegeaccounting.exception.FieldEmptyIllegalArgumentException;
import org.shevliakov.collegeaccounting.exception.GroupExistsException;

/**
 * Subcontroller for Groups tab.
 */
public class GroupsTabSubController {

  private final TextField groupTextField;
  private final TableView<Group> groupsTableView;
  private final TableColumn<?, ?> groupTableColumn;
  private final TableColumn<Group, Button> deleteGroupTableColumn;
  private final GroupRepository groupRepository;
  private List<Group> groups;
  private ObservableList<Group> groupsObservableList;

  /**
   * Constructor.
   *
   * @param groupTextField         TextField for entering group code.
   * @param groupsTableView        TableView for displaying groups.
   * @param groupTableColumn       TableColumn for displaying group code.
   * @param deleteGroupTableColumn TableColumn for deleting group.
   * @param groupRepository        Repository for working with groups.
   */
  public GroupsTabSubController(TextField groupTextField, TableView<Group> groupsTableView,
      TableColumn<?, ?> groupTableColumn, TableColumn<Group, Button> deleteGroupTableColumn,
      GroupRepository groupRepository) {
    this.groupTextField = groupTextField;
    this.groupsTableView = groupsTableView;
    this.groupTableColumn = groupTableColumn;
    this.deleteGroupTableColumn = deleteGroupTableColumn;
    this.groupRepository = groupRepository;
  }

  /**
   * Loads data from database and displays it in TableView.
   */
  public void loadData() {
    groups = groupRepository.getAll();
    groupsObservableList = groupsTableView.getItems();
    groupsObservableList.addAll(groups);
  }

  /**
   * Sets up columns in TableView.
   */
  public void setupTableColumns() {
    groupTableColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
    setupDeleteGroupColumn();
  }

  /**
   * Sets up column for deleting group.
   */
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

  /**
   * Adds group to database.
   */
  public void addGroup() {
    if (groupTextField.getText().isEmpty() || groupTextField.getText().isBlank()) {
      new FieldEmptyIllegalArgumentException("Група не може бути порожньою").showAlert();
      return;
    } else if (groupRepository.existsByCode(groupTextField.getText())) {
      new GroupExistsException("Така група вже існує").showAlert();
      return;
    }
    Group group = new Group();
    group.setCode(groupTextField.getText());
    groupRepository.save(group);
    refreshData();
  }

  /**
   * Refreshes data in TableView.
   */
  private void refreshData() {
    groups.clear();
    groupsObservableList.clear();
    groupTextField.clear();
    loadData();
    groupsTableView.refresh();
  }
}
