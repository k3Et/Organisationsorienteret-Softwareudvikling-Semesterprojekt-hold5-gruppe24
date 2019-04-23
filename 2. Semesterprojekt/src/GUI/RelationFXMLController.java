/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.ListOfEmployees;
import Domain.ListOfResidents;
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
    private ListView<User> residentListView;

    private ObservableList<User> obEmployeeList;
    private ObservableList<User> obRelationList;
    private ObservableList<User> obResidentList;

    private User selectedEmployee;
    private User selectedResident;
    private User selectedRelation;
    
    private SceneHandler sh = new SceneHandler();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obEmployeeList = FXCollections.observableArrayList();
        employeeListView.setItems(obEmployeeList);

        obRelationList = FXCollections.observableArrayList();
        relationListViews.setItems(obRelationList);

        obResidentList = FXCollections.observableArrayList();
        residentListView.setItems(obResidentList);

        obEmployeeList.addAll(ListOfEmployees.getEmployeesList());
    }

    @FXML
    private void addRelationBtn(ActionEvent event) {

        selectedResident = residentListView.getSelectionModel().getSelectedItem();
        if (selectedResident != null) {
            selectedEmployee.getRelations().add(selectedResident);
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
        obResidentList.clear();
        selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
        obRelationList.addAll(selectedEmployee.getRelations());
        obResidentList.addAll(selectedEmployee.getUnrelated());
    }

    private void updateListViews() {
        obRelationList.clear();
        obResidentList.clear();
        obRelationList.addAll(selectedEmployee.getRelations());
        obResidentList.addAll(selectedEmployee.getUnrelated());
    }

    @FXML
    private void handlerBtnBack(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Menu.fxml");
    }
    

}
