package GUI;

import Domain.Controller;
import Domain.DatabaseHandler;
import Domain.Diary;

import Domain.DiaryNote;
import Domain.ListOfResidents;
import Domain.User;
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

/**
 * FXML Controller class
 *
 * @author Sebastian
 */
public class DiaryFXMLController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button SaveNoteBtn;
    @FXML
    private TextArea WriteDiaryNote;
    @FXML
    private ListView<TextArea> ListOfDiaryNote;
    @FXML
    private ListView<User> residentListView;

    private ObservableList<TextArea> obList;
    private ObservableList<User> obResidents;

    private User selectedUser;

    private Diary diary;

    private DatabaseHandler dataHandler = new DatabaseHandler();

    private SceneHandler sh = new SceneHandler();
    @FXML
    private Button homeBtn;

    // private String writtenNote = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   c.setStockUsers();
        diary = new Diary();
        obList = FXCollections.observableArrayList();
        ListOfDiaryNote.setItems(obList);

//        for(int i = 0; i < ListOfResidents.list.size(); i++){  //addAll virkede ikke!
//              obResidents.add(ListOfResidents.list.get(i));
//       }
//     
        obResidents = FXCollections.observableArrayList();
        residentListView.setItems(obResidents);

        obResidents.addAll(ListOfResidents.getResidentList());

        ListOfDiaryNote.setEditable(false);
        readEmployeNoteFromDatabase();
        //ListOfDiaryNote.setStyle("-fx-opacity: 100;");

    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Menu.fxml");
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/LoginFXML.fxml");
    }

    @FXML
    private void SaveNoteBtnHandler(ActionEvent event) {

//        String writtenNote = WriteDiaryNote.getText();
//        String user = LoginFXMLController.currentUserLoggedIn;
//        String combines = writtenNote + "\n" + "skrevet af: " + user;
//        // d.setResidentName(selectedUser);
//        if (!writtenNote.equals("")) {
//            if (selectedUser == null) {
//                TextArea warning = new TextArea("Vælg venligst en beboer");
//                warning.setStyle("-fx-background-color: red;");
//                warning.setStyle("-fx-font-style: Bold");
//                obList.add(warning);
//            } else {
//
//                diary.saveDiaryNote(new DiaryNote(combines));
//                diary.convertDate();
//
//                WriteDiaryNote.setText("");
//                readFiles();
//            }
//        }
        saveNoteInDatabase();
    }

    private void saveNoteInDatabase() {
        String writtenNote = WriteDiaryNote.getText();
        String user = LoginFXMLController.currentUserLoggedIn;
        String combines = writtenNote + "\n" + "skrevet af: " + user + "\n" + dataHandler.convertDate();

        if (!writtenNote.equals("")) {
            if (selectedUser == null) {
                TextArea warning = new TextArea("Vælg venligst en beboer");
                warning.setStyle("-fx-background-color: red;");
                warning.setStyle("-fx-font-style: Bold");
                obList.add(warning);
            } else {

                Controller.saveNote(user, selectedUser, combines);

                WriteDiaryNote.setText("");
            }
        }
    }

    private void readFiles() {
        obList.clear();
        if (diary.getFiles().isEmpty()) {
            TextArea note = new TextArea("Indeholder ingen noter");
            obList.add(note);
        } else {

            for (int i = 0; i < diary.getFiles().size(); i++) {
                TextArea note = new TextArea(String.valueOf(diary.getFiles().get(i)) + " " + diary.getFileName().get(i));
                note.setEditable(false);
                note.setStyle("-fx-background-color: lightblue;");
                obList.add(note);
            }
        }
    }

    private void readEmployeNoteFromDatabase() {
        obList.clear();
        String fullDate = dataHandler.convertDate();
        String date = fullDate.substring(0, 2);
        String user = LoginFXMLController.currentUserLoggedIn;
        for (String s : dataHandler.getEmployeeNote(user, date)) {
            TextArea note = new TextArea(s);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
            System.out.println("yolo");
        }

    }

    private void readResidentNoteFromDatabase() {
        obList.clear();
        String fullDate = dataHandler.convertDate();
        String date = fullDate.substring(0, 2);
        String user = selectedUser.getName();
        for (String s : dataHandler.getResidentNote(user, date)) {
            TextArea note = new TextArea(s);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
            System.out.println("yolo");
        }

    }

    @FXML
    private void onResidentClickedHandler(MouseEvent event) {
        selectedUser = residentListView.getSelectionModel().getSelectedItem(); //finds the selected item  

        diary.setResidentName(selectedUser);
        if (selectedUser != null) {
            ListOfDiaryNote.scrollTo(obList.size());
            //readFiles();
            readResidentNoteFromDatabase();
        }
        //tag en string, RESIDENT NAME og brug den i en metode i diarys argument
        //loadUserNotes()
    }

    @FXML
    private void HomeBtnHandler(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Menu.fxml");
    }

}
