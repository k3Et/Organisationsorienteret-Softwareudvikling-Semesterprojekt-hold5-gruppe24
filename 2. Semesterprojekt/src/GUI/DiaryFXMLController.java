/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.DiaryList;
import Domain.DiaryNote;
import Domain.ListOfPatients;
import Domain.User;
import java.net.URL;
import java.util.List;
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
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Text DagbogBtn;
    @FXML
    private TextArea WriteDiaryNote;
    @FXML
    private Button SaveNoteBtn;
    @FXML
    private ListView<TextArea> ListOfDiaryNote;

    ObservableList<TextArea> obList;

    String writtenNote = "";
    @FXML
    private ListView<User> patientListView;

    ObservableList<User> obPatients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obList = FXCollections.observableArrayList();
        ListOfDiaryNote.setItems(obList);

        System.out.println(ListOfPatients.getPatientList());

//        for(int i = 0; i < ListOfPatients.list.size(); i++){  //addAll virkede ikke!
//              obPatients.add(ListOfPatients.list.get(i));
//       }
//     
//        obPatients.addAll(ListOfPatients.getPatientList());

        System.out.println("ob" + obPatients);
        //   obPatients =  FXCollections.observableArrayList();
        // patientListView.setItems(obPatients);

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
    private void handleDagbogTextClickAction(MouseEvent event) {
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
