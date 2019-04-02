package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
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
    @FXML
    private Label resultLabel;

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
        if (verifyLogin(userNameField.getText(), passWordField.getText())) {
            resultLabel.setText("Logger ind...");
        } else {
            resultLabel.setText("Brugernavn eller\nPassword forkert..");
        }
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        //Create verification with SQL database here:
        if (verifyLogin(userNameField.getText(), passWordField.getText())) {
            resultLabel.setText("Logger ind...");
        } else {
            resultLabel.setText("Brugernavn eller Password forkert..");
        }
    }

    public boolean verifyLogin(String username, String password) {

        File file = new File("src/Data/LoginData.txt");
        try (Scanner scan = new Scanner(file);) {
            String tot = "";
            while (scan.hasNext()) {
                tot += scan.nextLine();
            }
            String[] data = tot.split(";");
            for (int i = 0; i < data.length; i += 2) {
                if (data[i].equals(username) && data[i + 1].equals(password)) {
                    return true;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return false;

    }
}