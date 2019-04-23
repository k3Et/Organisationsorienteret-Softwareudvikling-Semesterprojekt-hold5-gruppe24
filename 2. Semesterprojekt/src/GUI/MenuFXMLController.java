package GUI;

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
 * @author Victor
 */
public class MenuFXMLController implements Initializable {

    private ListView<String> menuListView;
    @FXML
    private Button logOutButton;

    private String selectedMenu;
    
    private ObservableList<String> OListDairyNote;


    @FXML
    private Button homeButton;

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
