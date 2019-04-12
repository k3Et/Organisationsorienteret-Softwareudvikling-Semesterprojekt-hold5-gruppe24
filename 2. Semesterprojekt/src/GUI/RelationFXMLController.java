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

    private User selectedEmployee;
    private User selectedPatient;
    private User selectedRelation;

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

        selectedPatient = patientListView.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            selectedEmployee.getRelations().add(selectedPatient);
            updateListViews();
        }

    }

    @FXML
    private void removeRelationBtn(ActionEvent event) {

        selectedRelation = relationListViews.getSelectionModel().getSelectedItem();
        selectedEmployee.getRelations().remove(selectedRelation);
        updateListViews();

    }

    @FXML
    private void handleMouseOnEmployee(MouseEvent event) {

        obRelationList.clear();
        obPatientList.clear();
        selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
        obRelationList.addAll(selectedEmployee.getRelations());
        obPatientList.addAll(selectedEmployee.getUnrelated());
    }

    private void updateListViews() {
        obRelationList.clear();
        obPatientList.clear();
        obRelationList.addAll(selectedEmployee.getRelations());
        obPatientList.addAll(selectedEmployee.getUnrelated());
    }

}
