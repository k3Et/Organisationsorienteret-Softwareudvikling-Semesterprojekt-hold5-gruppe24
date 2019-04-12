package GUI;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;
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
    private ListView<?> menuListView;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Text patientTextBtn;
    @FXML
    private ListView<?> ListOfDiaryNote;

    ObservableList<TextArea> OListDairyNote;
    @FXML
    private Text DagbogBtn;
    @FXML
    private Text AnsatteBtn;
    @FXML
    private TextArea WriteDiaryNote;
    @FXML
    private Button SaveNoteBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
    }

    @FXML
    private void handlePatientTextClickAction(MouseEvent event) {
    }

    @FXML
    private void handleDagbogTextClickAction(MouseEvent event) {
    }

    @FXML
    private void handleAnsatteTextClickAction(MouseEvent event) {
    }
  
}
