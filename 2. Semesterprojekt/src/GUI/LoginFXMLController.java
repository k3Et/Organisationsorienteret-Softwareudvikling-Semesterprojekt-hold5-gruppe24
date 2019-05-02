package GUI;

import Domain.DatabaseHandler;
import static GUI.SceneHandler.currentScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginFXMLController implements Initializable {
    
    private ImageView loginImageView;
    @FXML
    private ImageView NoTiBoImage;
    @FXML
    private PasswordField passWordField;
    @FXML
    private Label passWordLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private Button loginButton;
    @FXML
    private ComboBox<String> comboBox;
    
    private ObservableList<String> locations = FXCollections.observableArrayList();
    
    private SceneHandler sh = new SceneHandler();
    
    public static String currentUserLoggedIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ChoiceBox:
        locations.add("Odense Boligø");
        locations.add("Odense Misbrugercenter");
        locations.add("København Boligcentral");
        comboBox.setItems(locations);
        //comboBox.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void handlePassWordFIeldAction(ActionEvent event) {
        //Create verification with SQL database here:
        if (DatabaseHandler.verifyLogin(userNameField.getText(), passWordField.getText())) {
            currentUserLoggedIn = userNameField.getText();
            resultLabel.setText("Logger ind...");
            sh.setNewScene("/GUI/FXML/Menu.fxml");
//            String css = MenuFXMLController.class.getResource("Menu.css").toExternalForm();
//            currentScene.getStylesheets().add(css);
//            System.out.println("css");
        } else {
            resultLabel.setText("Brugernavn eller\nPassword forkert..");
        }
    }
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        //Create verification with SQL database here:
        if (DatabaseHandler.verifyLogin(userNameField.getText(), passWordField.getText())) {
            currentUserLoggedIn = userNameField.getText();
            resultLabel.setText("Logger ind...");
            sh.setNewScene("/GUI/FXML/Menu.fxml");
            
            String css = MenuFXMLController.class.getResource("Menu.css").toExternalForm();
            currentScene.getStylesheets().add(css);
            System.out.println("css");
            
        } else {
            resultLabel.setText("Brugernavn eller\nPassword forkert..");
        }
    }
    
}