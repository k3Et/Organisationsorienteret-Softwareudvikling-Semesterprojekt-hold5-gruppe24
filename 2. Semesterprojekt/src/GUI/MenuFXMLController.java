package GUI;

import static GUI.LoginFXMLController.currentUserLoggedIn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class MenuFXMLController implements Initializable {

    private SceneHandler sh = new SceneHandler();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/LoginFXML.fxml");
    }

    @FXML
    private void diaryBtnHandler(ActionEvent event) {
        if (currentUserLoggedIn.getPermissions().contains("create note")) {
            sh.setNewScene("/GUI/FXML/Diary.fxml");
        }
    }

    @FXML
    private void relationBtnHandler(ActionEvent event) {
        if (currentUserLoggedIn.getPermissions().contains("ansatte relation")) {
            sh.setNewScene("/GUI/FXML/RelationFXML.fxml");
        }
    }

    @FXML
    private void employeeRoleBtnHandler(ActionEvent event) {
        if (currentUserLoggedIn.getPermissions().contains("bruger rolle")) {
            sh.setNewScene("/GUI/FXML/AssignRole.fxml");
        }
    }
}
