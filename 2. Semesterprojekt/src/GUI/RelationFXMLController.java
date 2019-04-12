/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.ListOfEmployees;
import Domain.ListOfPatients;
import Domain.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class RelationFXMLController implements Initializable {

    @FXML
    private ListView<User> employeeListView;
    @FXML
    private ListView<User> relationListViews;
    @FXML
    private ListView<User> patientListView;

    private ObservableList<User> obEmployeeList;
    private ObservableList<User> obRelationList;
    private ObservableList<User> obPatientList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obEmployeeList = FXCollections.observableArrayList();
        employeeListView.setItems(obEmployeeList);

        obRelationList = FXCollections.observableArrayList();
        relationListViews.setItems(obRelationList);

        obPatientList = FXCollections.observableArrayList();
        patientListView.setItems(obPatientList);

        obEmployeeList.addAll(ListOfEmployees.getEmployeesList());
    }

    @FXML
    private void addRelationBtn(ActionEvent event) {

    }

    @FXML
    private void removeRelationBtn(ActionEvent event) {
    }

    @FXML
    private void handleMouseOnEmployee(MouseEvent event) {
        User selectedUser = employeeListView.getSelectionModel().getSelectedItem();
        obRelationList.addAll(selectedUser.getRelations());
    }

}
