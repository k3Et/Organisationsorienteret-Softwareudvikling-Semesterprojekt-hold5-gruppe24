/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.DiaryList;
import Domain.DiaryNote;
import java.net.URL;
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
 * @author Sebas
 */
public class DiaryFXMLController implements Initializable {

    @FXML
    private ListView<?> menuListView;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Text patientTextBtn;
    @FXML
    private Text DagbogBtn;
    @FXML
    private Text AnsatteBtn;
    @FXML
    private TextArea WriteDiaryNote;
    @FXML
    private Button SaveNoteBtn;
    @FXML
    private ListView<TextArea> ListOfDiaryNote;

    ObservableList<TextArea> obList;

    String writtenNote = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obList = FXCollections.observableArrayList();
        ListOfDiaryNote.setItems(obList);

        ListOfDiaryNote.setEditable(false);
        //ListOfDiaryNote.setStyle("-fx-opacity: 100;");
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

    DiaryList dl = new DiaryList();

    @FXML
    private void SaveNoteBtnHandler(ActionEvent event) {

        writtenNote = WriteDiaryNote.getText();
        TextArea note = new TextArea(writtenNote);
        note.setEditable(false);
        note.setStyle("-fx-background-color: lightblue;");

        new DiaryList().saveDiaryNote(new DiaryNote(writtenNote));
        System.out.println(dl.convertDate());
        dl.convertDate();
        obList.add(note);
        WriteDiaryNote.setText(null);

    }

}
