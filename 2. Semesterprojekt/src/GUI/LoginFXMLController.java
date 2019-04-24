package GUI;

import Domain.DatabaseHandler;
import Domain.FileHandler;
import static GUI.SceneHandler.currentScene;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    private Random r = new Random();

    private Image[] loginImages;

    private SceneHandler sh = new SceneHandler();

    private DatabaseHandler dh = new DatabaseHandler();

    private FileHandler fh = new FileHandler();

    public static String currentUserLoggedIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initializing the fruit images through a for loop.
        loginImages = new Image[3];
        String pre = "/Pictures/loginPicture";
        String post = ".jpg";
        for (int i = 0; i < loginImages.length; i++) {
            loginImages[i] = new Image((pre + i + post));
        }
        //Using Random to set login image.
        
        NoTiBoImage.setImage(new Image("/Pictures/NoTiBoImage.png"));
    }

    @FXML
    private void handlePassWordFIeldAction(ActionEvent event) {
        //Create verification with SQL database here:
        if (fh.verifyLogin(userNameField.getText(), passWordField.getText())) {
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
        if (dh.verifyLogin(userNameField.getText(), passWordField.getText())) {
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