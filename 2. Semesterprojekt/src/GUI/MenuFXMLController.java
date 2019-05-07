package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private Button logOutButton;
    @FXML
    private ImageView NoTiBoImage;
    @FXML
    private Button homeButton;

    private String selectedMenu;

    private ObservableList<String> OListDairyNote;

    private ListView<String> menuListView;

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

    private void handlerOpenMenu(MouseEvent event) {

    }

    @FXML
    private void diaryBtnHandler(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Diary.fxml");
    }

    @FXML
    private void relationBtnHandler(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/RelationFXML.fxml");
    }

    @FXML
    private void employeeRoleBtnHandler(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/AssignRole.fxml");
    }

}
