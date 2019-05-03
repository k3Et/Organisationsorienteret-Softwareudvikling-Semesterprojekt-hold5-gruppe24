/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class ResidentInformationController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField cprField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField adresseField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setNameField(String s){
        nameField.setText(s);
    }

    public void setCprField(String s) {
        cprField.setText(s);
    }

    public void setPhoneField(String s) {
        phoneField.setText(s);
    }

    public void setEmailField(String s) {
        emailField.setText(s);
    }

    public void setAdresseField(String s) {
        adresseField.setText(s);
    }
    
    
    @FXML
    private void CloseHandler(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
