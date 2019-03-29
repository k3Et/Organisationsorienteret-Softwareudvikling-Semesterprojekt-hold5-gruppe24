package GUI;

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

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private ImageView loginImageView;
    @FXML
    private PasswordField passWordField;
    @FXML
    private Label passWordLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView NoTiBoImage;

    Random r = new Random();
    Image[] loginImages;

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
        loginImageView.setImage(loginImages[r.nextInt(3)]);
        NoTiBoImage.setImage(new Image("/Pictures/NoTiBoImage.png"));
    }

    @FXML
    private void handlePassWordFIeldAction(ActionEvent event) {
        //Create verification with SQL database here:
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        //Create verification with SQL database here:
    }

}