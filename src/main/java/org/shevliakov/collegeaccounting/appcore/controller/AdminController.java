package org.shevliakov.collegeaccounting.appcore.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.shevliakov.collegeaccounting.appcore.subcontroller.CategoriesTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.GroupsTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.PedagogicalTitlesTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.RanksTabSubController;
import org.shevliakov.collegeaccounting.appcore.subcontroller.UsersTabSubController;
import org.shevliakov.collegeaccounting.database.config.SpringConfig;
import org.shevliakov.collegeaccounting.database.repository.GroupRepository;
import org.shevliakov.collegeaccounting.database.repository.PedagogicalTitleRepository;
import org.shevliakov.collegeaccounting.database.repository.QualificationCategoryRepository;
import org.shevliakov.collegeaccounting.database.repository.RankRepository;
import org.shevliakov.collegeaccounting.database.repository.UserRepository;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Controller for the admin scene.
 */
public class AdminController implements Initializable {

  @FXML
  private TextField pedagogicalTitleTextField;
  @FXML
  private TableView<PedagogicalTitle> pedagogicalTitlesTableView;
  @FXML
  private TableColumn<?, ?> pedagogicalTitleTableColumn;
  @FXML
  private TableColumn<PedagogicalTitle, Button> deletePedagogicalTitleTableColumn;
  @FXML
  private TextField groupTextField;
  @FXML
  private TableView<Group> groupsTableView;
  @FXML
  private TableColumn<?, ?> groupTableColumn;
  @FXML
  private TableColumn<Group, Button> deleteGroupTableColumn;
  @FXML
  private TextField rankTextField;
  @FXML
  private TableView<Rank> ranksTableView;
  @FXML
  private TableColumn<?, ?> rankTableColumn;
  @FXML
  private TableColumn<Rank, Button> deleteRankTableColumn;
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
  @FXML
  private TableView<QualificationCategory> qualificationCategoriesTableView;
  @FXML
  private TableColumn<?, ?> qualificationCategoryTableColumn;
  @FXML
  private TableColumn<QualificationCategory, Button> deleteQualificationCategoryTableColumn;
  @FXML
  private TextField qualificationCategoryTextField;
  private RanksTabSubController ranksTabSubController;
  private GroupsTabSubController groupsTabSubController;
  private CategoriesTabSubController categoriesTabSubController;
  private UsersTabSubController usersTabSubController;
  private PedagogicalTitlesTabSubController pedagogicalTitlesTabSubController;

  /**
   * Initialize the controller.
   *
   * @param url            the URL
   * @param resourceBundle the resource bundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    usersTabSubController = new UsersTabSubController(usernameTextField, usersTableView,
        usernameTableColumn, isAdministratorTableColumn, readAndWritePermissionTableColumn,
        deleteUserTableColumn, context.getBean(UserRepository.class));
    usersTabSubController.loadData();
    usersTabSubController.setupTableColumns();
    usersTabSubController.setupFiltering();

    ranksTabSubController = new RanksTabSubController(rankTextField, ranksTableView,
        rankTableColumn, deleteRankTableColumn, context.getBean(RankRepository.class));
    ranksTabSubController.loadData();
    ranksTabSubController.setupTableColumns();

    groupsTabSubController = new GroupsTabSubController(groupTextField, groupsTableView,
        groupTableColumn, deleteGroupTableColumn, context.getBean(GroupRepository.class));
    groupsTabSubController.loadData();
    groupsTabSubController.setupTableColumns();

    categoriesTabSubController = new CategoriesTabSubController(qualificationCategoryTextField,
        qualificationCategoriesTableView, qualificationCategoryTableColumn,
        deleteQualificationCategoryTableColumn,
        context.getBean(QualificationCategoryRepository.class));
    categoriesTabSubController.loadData();
    categoriesTabSubController.setupTableColumns();

    pedagogicalTitlesTabSubController = new PedagogicalTitlesTabSubController(
        pedagogicalTitleTextField, pedagogicalTitlesTableView, pedagogicalTitleTableColumn,
        deletePedagogicalTitleTableColumn, context.getBean(PedagogicalTitleRepository.class));
    pedagogicalTitlesTabSubController.loadData();
    pedagogicalTitlesTabSubController.setupTableColumns();
  }

  public void onAddRankButtonClicked() {
    ranksTabSubController.addRank();
  }

  public void onAddGroupButtonClicked() {
    groupsTabSubController.addGroup();
  }

  public void onAddQualificationCategoryButtonClicked() {
    categoriesTabSubController.addQualificationCategory();
  }

  public void onAddPedagogicalTitleButtonClicked() {
    pedagogicalTitlesTabSubController.addPedagogicalTitle();
  }
}
