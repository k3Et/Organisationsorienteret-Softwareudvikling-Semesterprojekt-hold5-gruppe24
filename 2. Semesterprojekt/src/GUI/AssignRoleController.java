package GUI;

import Domain.Controller;
import Domain.ListOfUsers;
import Domain.Role;
import Domain.Roles.Employee;
import Domain.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class AssignRoleController implements Initializable {

    @FXML
    private ListView<User> chooseUserList;
    @FXML
    private Button acceptBtn;
    @FXML
    private ListView<Role> chooseRoleList;
    @FXML
    private Button cancelBtn;

    private ObservableList<User> userList;
    private ObservableList<Role> roleList;

    private ObservableList<String> userInfoList;
    //ListOfUsers lou ;
    Controller c = new Controller();
    @FXML
    private ListView<String> userInfoListView;

    private Scanner s;
    private String stringHolder;

    private User selectedUser;
    private Role selectedRole;

    private SceneHandler sh = new SceneHandler();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userList = FXCollections.observableArrayList();
        chooseUserList.setItems(userList);

        roleList = FXCollections.observableArrayList();
        chooseRoleList.setItems(roleList);

        userInfoList = FXCollections.observableArrayList();
        userInfoListView.setItems(userInfoList);

        c.setStockUsers();
        c.setStockRoleList();
        userList.addAll(c.getStockUsers());
        roleList.addAll(c.getStockRoleList());
        //showUsers();

    }

    public void showUsers() {
        //  System.out.println(lou.users);
        //userList.addAll(lou.users);

    }

    @FXML
    private void acceptBtnHandler(ActionEvent event) {
        selectedUser = chooseUserList.getSelectionModel().getSelectedItem(); //finds the selected item 
        System.out.println(selectedUser + " " + selectedUser.getRoles());

        selectedRole = chooseRoleList.getSelectionModel().getSelectedItem();
        System.out.println(selectedRole);

        if (selectedRole != null) {
            addRoleToUser(selectedUser, selectedRole);
        }
        loadUserInfo(selectedUser);

    }

    public void loadUserInfo(User selectedUser) {

        s = new Scanner(selectedUser.toStringAll());

        stringHolder = s.nextLine();

        String[] infoStringArray = stringHolder.split(";");

        for (int i = 0; i < infoStringArray.length; i++) {
            userInfoList.add(infoStringArray[i]);
        }

    }

    public void addRoleToUser(User selectedUser, Role selectedRole) {
        if (!selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().addRole(selectedRole);
        }
    }

    public void removeRoleFromUser(User selectedUser, Role selectedRole) {
        if (selectedUser.getRoles().contains(selectedRole)) {
            selectedUser.getRoleList().removeRole(selectedRole);
        }
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
        userInfoList.clear();
        chooseRoleList.getSelectionModel().clearSelection();
        chooseUserList.getSelectionModel().clearSelection();
    }

    @FXML
    private void sletRolleBtnHandler(ActionEvent event) {
        selectedUser = chooseUserList.getSelectionModel().getSelectedItem();
        selectedRole = chooseRoleList.getSelectionModel().getSelectedItem();
        removeRoleFromUser(selectedUser, selectedRole);
        loadUserInfo(selectedUser);
    }

    @FXML
    private void addNewUserBtn(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/NewUser.fxml");
    }

}
