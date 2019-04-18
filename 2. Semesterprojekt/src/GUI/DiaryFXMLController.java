/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Controller;
import Domain.Diary;

import Domain.DiaryNote;
import Domain.ListOfPatients;
import Domain.User;
import java.io.File;
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

    // private String writtenNote = "";
    @FXML
    private ListView<User> patientListView;

    ObservableList<User> obPatients;
    Controller c = new Controller();
    private User selectedUser;
    private Diary diary;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c.setStockUsers();
        diary = new Diary();
        obList = FXCollections.observableArrayList();
        ListOfDiaryNote.setItems(obList);

//        for(int i = 0; i < ListOfPatients.list.size(); i++){  //addAll virkede ikke!
//              obPatients.add(ListOfPatients.list.get(i));
//       }
//     
        obPatients = FXCollections.observableArrayList();
        patientListView.setItems(obPatients);

        obPatients.addAll(ListOfPatients.getPatientList());
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

    @FXML
    private void onPatientClickedHandler(MouseEvent event) {
        selectedUser = patientListView.getSelectionModel().getSelectedItem(); //finds the selected item  

        diary.setPatientName(selectedUser);

        //tag en string, PATIENT NAME og brug den i en metode i diarys argument
        //loadUserNotes()
    }

    @FXML
    private void SaveNoteBtnHandler(ActionEvent event) {

        String writtenNote = WriteDiaryNote.getText();

        // d.setPatientName(selectedUser);
        if (!writtenNote.equals("")) {
            TextArea note = new TextArea(writtenNote);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
            System.out.println("CONTROLLER " + writtenNote);
            diary.saveDiaryNote(new DiaryNote(writtenNote));
            diary.convertDate();

            WriteDiaryNote.setText("");

        }
    }

    @FXML
    private void openBtnHandler(ActionEvent event) {
        for (int i = 0; i < diary.getFiles().size(); i++) {

            TextArea note = new TextArea(String.valueOf(diary.getFiles().get(i)));
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
        }
    }

}
