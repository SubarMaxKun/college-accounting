package org.shevliakov.collegeaccounting.appcore.subcontroller;

import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;

/**
 * Subcontroller for the Users tab.
 */
public class UsersTabSubController {

  private final TextField usernameTextField;
  private final TableView<User> usersTableView;
  private final TableColumn<?, ?> usernameTableColumn;
  private final TableColumn<User, Boolean> isAdministratorTableColumn;
  private final TableColumn<User, Boolean> readAndWritePermissionTableColumn;
  private final TableColumn<User, Button> deleteUserTableColumn;
  private final UserRepository userRepository;
  private List<User> users;
  private ObservableList<User> usersObservableList;

  /**
   * Constructor.
   *
   * @param usernameTextField                 The text field for filtering users by username.
   * @param usersTableView                    The table view for displaying users.
   * @param usernameTableColumn               The table column for displaying usernames.
   * @param isAdministratorTableColumn        The table column for displaying checkboxes for setting
   *                                          administrator permissions.
   * @param readAndWritePermissionTableColumn The table column for displaying checkboxes for setting
   *                                          read and write permissions.
   * @param deleteUserTableColumn             The table column for displaying buttons for deleting
   *                                          users.
   * @param userRepository                    The repository for users.
   */
  public UsersTabSubController(TextField usernameTextField, TableView<User> usersTableView,
      TableColumn<?, ?> usernameTableColumn, TableColumn<User, Boolean> isAdministratorTableColumn,
      TableColumn<User, Boolean> readAndWritePermissionTableColumn,
      TableColumn<User, Button> deleteUserTableColumn, UserRepository userRepository) {
    this.usernameTextField = usernameTextField;
    this.usersTableView = usersTableView;
    this.usernameTableColumn = usernameTableColumn;
    this.isAdministratorTableColumn = isAdministratorTableColumn;
    this.readAndWritePermissionTableColumn = readAndWritePermissionTableColumn;
    this.deleteUserTableColumn = deleteUserTableColumn;
    this.userRepository = userRepository;
  }

  /**
   * Loads data from the database and displays it in the table view.
   */
  public void loadData() {
    users = userRepository.getAll();
    usersObservableList = usersTableView.getItems();
    usersObservableList.addAll(users);
  }

  /**
   * Sets up the columns of the table view.
   */
  public void setupTableColumns() {
    usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    setupIsAdministratorColumn();
    setupReadAndWritePermissionColumn();
    setupDeleteUserColumn();
  }

  /**
   * Sets up the filtering of the table view.
   */
  private void setupIsAdministratorColumn() {
    isAdministratorTableColumn.setCellValueFactory(cellData -> {
      SimpleBooleanProperty property = new SimpleBooleanProperty(cellData.getValue().getAdmin());
      property.addListener((obs, oldValue, newValue) -> {
        // Handle checkbox changes here
        User user = cellData.getValue();
        user.setAdmin(newValue);
        userRepository.save(user);
      });
      return property;
    });
    isAdministratorTableColumn.setCellFactory(column -> new CheckBoxTableCell<>());
  }

  /**
   * Sets up the read and write permission column of the table view.
   */
  private void setupReadAndWritePermissionColumn() {
    readAndWritePermissionTableColumn.setCellValueFactory(cellData -> {
      SimpleBooleanProperty property = new SimpleBooleanProperty(
          cellData.getValue().getReadAndWritePermission());
      property.addListener((obs, oldValue, newValue) -> {
        // Handle checkbox changes here
        User user = cellData.getValue();
        user.setReadAndWritePermission(newValue);
        userRepository.save(user);
      });
      return property;
    });
    readAndWritePermissionTableColumn.setCellFactory(column -> new CheckBoxTableCell<>());
  }

  /**
   * Sets up the delete user column of the table view.
   */
  private void setupDeleteUserColumn() {
    Callback<TableColumn<User, Button>, TableCell<User, Button>> cellFactory = param -> new TableCell<>() {
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
            User user = getTableView().getItems().get(getIndex());
            userRepository.delete(user);
            refreshData();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    deleteUserTableColumn.setCellFactory(cellFactory);
  }

  /**
   * Sets up the filtering of the table view.
   */
  public void setupFiltering() {
    usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        usersObservableList.clear();
        usersObservableList.addAll(users);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        usersObservableList.clear();
        usersObservableList.addAll(users);
        usersObservableList.removeIf(
            user -> !user.getUsername().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }

  /**
   * Refreshes the data in the table view.
   */
  public void refreshData() {
    users.clear();
    usersObservableList.clear();
    usernameTextField.clear();
    loadData();
    usersTableView.refresh();
  }
}
