package org.shevliakov.collegeaccounting.appcore.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.AdminSubController;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AdminController implements Initializable {

  @FXML
  private TextField usernameTextField;
  @FXML
  private TableView<User> usersTableView;
  @FXML
  private TableColumn<?, ?> usernameTableColumn;
  @FXML
  private TableColumn<User, Boolean> isAdministratorTableColumn;
  @FXML
  private TableColumn<User, Boolean> readAndWritePermissionTableColumn;
  @FXML
  private TableColumn<User, Button> deleteUserTableColumn;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    AdminSubController adminSubController = new AdminSubController(usernameTextField,
        usersTableView,
        usernameTableColumn, isAdministratorTableColumn, readAndWritePermissionTableColumn,
        deleteUserTableColumn, context.getBean(UserRepository.class));
    adminSubController.loadData();
    adminSubController.setupTableColumns();
    adminSubController.setupFiltering();
  }
}
