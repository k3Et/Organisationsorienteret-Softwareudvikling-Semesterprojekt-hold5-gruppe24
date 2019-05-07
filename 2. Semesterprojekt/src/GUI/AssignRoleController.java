package GUI;

import Domain.Controller;

import Domain.Role;
import Domain.User;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

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
    private ListView<Role> chooseRoleList;
    @FXML
    private ListView<String> userInfoListView;
    @FXML
    private Button acceptBtn;

    private ObservableList<User> userList;
    private ObservableList<Role> roleList;
    private ObservableList<String> userInfoList;
    //ListOfUsers lou ;

    private Scanner s;

    private String stringHolder;

    private User selectedUser;

    private Role selectedRole;

    SceneHandler sh = new SceneHandler();

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

        userList.addAll(Controller.getStockUsers());
        roleList.addAll(Controller.getStockRoleList());
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
            Controller.addRoleToUser(selectedUser, selectedRole);
        }
        updateListViews();
    }

    public void loadUserInfo(User selectedUser) {
        stringHolder = selectedUser.toStringAll();

        String[] infoStringArray = stringHolder.split(";");

        for (int i = 0; i < infoStringArray.length; i++) {
            userInfoList.add(infoStringArray[i]);
        }

    }

    private void cancelBtnHandler(ActionEvent event) {
        userInfoList.clear();
        chooseRoleList.getSelectionModel().clearSelection();
        chooseUserList.getSelectionModel().clearSelection();
    }

    @FXML
    private void sletRolleBtnHandler(ActionEvent event) {
        selectedUser = chooseUserList.getSelectionModel().getSelectedItem();
        selectedRole = chooseRoleList.getSelectionModel().getSelectedItem();
        Controller.removeRoleFromUser(selectedUser, selectedRole);

        updateListViews();

    }

    @FXML
    private void addNewUserBtn(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/FXML/NewUser.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tilføj bruger");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SceneHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleUserChosen(MouseEvent event) {
        selectedUser = chooseUserList.getSelectionModel().getSelectedItem();
        updateListViews();
    }

    private void updateListViews() {
        userInfoList.clear();
        loadUserInfo(selectedUser);

    }

    @FXML
    private void handlerBtnBack(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Menu.fxml");
    }

}
