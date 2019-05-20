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
        nameField.setEditable(false);
        cprField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);
        adresseField.setEditable(false);
    }

    public void setNameField(String s) {
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
