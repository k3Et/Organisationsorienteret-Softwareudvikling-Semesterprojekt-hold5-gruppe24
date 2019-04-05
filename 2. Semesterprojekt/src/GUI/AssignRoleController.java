/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramiy
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
    private String[] infoStringArray;

    private User selectedUser;
    private Role selectedRole;
    @FXML
    private Button newUserBtn;

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
        System.out.println(selectedUser + " " + selectedUser.getRole());

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

        infoStringArray = stringHolder.split(";");

        for (int i = 0; i < infoStringArray.length; i++) {
            userInfoList.add(infoStringArray[i]);
        }

    }

    public void addRoleToUser(User selectedUser, Role selectedRole) {
        if (!selectedUser.roles.getRoleList().contains(selectedRole)) {
            selectedUser.roles.addRole(selectedRole);
        }
    }

    public void removeRoleFromUser(User selectedUser, Role selectedRole) {
        if (selectedUser.roles.getRoleList().contains(selectedRole)) {
            selectedUser.roles.removeRole(selectedRole);
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

        removeRoleFromUser(selectedUser, selectedRole);
        loadUserInfo(selectedUser);
    }

    @FXML
    private void newUserBtnHandler(ActionEvent event, Stage stage) {
           Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/FXML/AssignRole.fxml"));
        

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("NoTiBo - Noter til Dagbøger");
        // stage.setMaximized(true);
        stage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(AssignRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
