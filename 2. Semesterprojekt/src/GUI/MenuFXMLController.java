package GUI;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private ListView<String> menuListView;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;

    private String selectedMenu;
    ObservableList<String> OListDairyNote;

    SceneHandler sh = new SceneHandler();
    @FXML
    private Button homeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        
        OListDairyNote = FXCollections.observableArrayList();
        menuListView.setItems(OListDairyNote);

        OListDairyNote.add("dagbog");
        OListDairyNote.add("roller");
        OListDairyNote.add("relationer");
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/LoginFXML.fxml");
    }

    @FXML
    private void handlerOpenMenu(MouseEvent event) {
        selectedMenu = menuListView.getSelectionModel().getSelectedItem();
        if (selectedMenu.equals("dagbog")) {
            sh.setNewScene("/GUI/FXML/Diary.fxml");
        } else if (selectedMenu.equals("roller")) {
            sh.setNewScene("/GUI/FXML/AssignRole.fxml");
        } else if (selectedMenu.equals("relationer")) {
            sh.setNewScene("/GUI/FXML/RelationFXML.fxml");
        }
    }

}
