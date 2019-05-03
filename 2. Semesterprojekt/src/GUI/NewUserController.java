package GUI;

import Domain.Controller;
import Domain.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class NewUserController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField mobilTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private TextField cprTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
//        Controller.createNewUser(nameTextField.getText(), passwordTextField.getText(), usernameTextField.getText(), cprTextField.getText(), mobilTextField.getText(), emailTextField.getText(), adresseTextField.getText());
        Controller.createUserInDatabase(nameTextField.getText(), passwordTextField.getText(), usernameTextField.getText(), cprTextField.getText(), mobilTextField.getText(), emailTextField.getText(), adresseTextField.getText());
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
