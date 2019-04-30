package GUI;

import Domain.Diary;

import Domain.DiaryNote;
import Domain.ListOfResidents;
import Domain.Medicine;
import Domain.MedicineList;
import Domain.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private SceneHandler sh = new SceneHandler();
    @FXML
    private Button homeBtn;

    @FXML
    private TableColumn<Medicine, String> MedicineCol;
    @FXML
    private TableColumn<TableGetterSetter, ChoiceBox> DosisCol;
    @FXML
    private TableView<TableGetterSetter> tableView;

    private ObservableList<TableGetterSetter> data = FXCollections.observableArrayList();
    private ObservableList<String> choiceList = FXCollections.observableArrayList();
    private MedicineList medicineList = new MedicineList();

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

        loadDosis();
        for (int i = 0; i < medicineList.getMedicineList().size(); i++) {

            ChoiceBox choose = new ChoiceBox();
            choose.getItems().addAll(choiceList);
            choose.getSelectionModel().selectFirst();
            data.add(new TableGetterSetter(medicineList.getMedicineList().get(i).getName(), choose));
        }

        tableView.setItems(data);

        MedicineCol.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        DosisCol.setCellValueFactory(new PropertyValueFactory<TableGetterSetter, ChoiceBox>("choiceBox"));

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

        for (String s : getTableViewValues()) {
            System.out.println(s);
        }
        String writtenNote = WriteDiaryNote.getText();
        String user = LoginFXMLController.currentUserLoggedIn;
        String combines = writtenNote + "\n" + "skrevet af: " + user;
        // d.setResidentName(selectedUser);
        if (!writtenNote.equals("")) {
            if (selectedUser == null) {
                TextArea warning = new TextArea("Vælg venligst en beboer");
                warning.setStyle("-fx-background-color: red;");
                warning.setStyle("-fx-font-style: Bold");
                obList.add(warning);
            } else {

                diary.saveDiaryNote(new DiaryNote(combines));
                diary.convertDate();

                WriteDiaryNote.setText("");
                readFiles();
            }
        }
    }

    private ArrayList<String> getTableViewValues() {
        ArrayList<String> values = new ArrayList<>();
        ObservableList<TableColumn<TableGetterSetter, ?>> columns = tableView.getColumns();

        for (Object row : tableView.getItems()) {
            for (TableColumn column : columns) {
                if (column.getCellObservableValue(row).getValue() instanceof ChoiceBox) {
                    ChoiceBox cb = (ChoiceBox)column.getCellObservableValue(row).getValue();
                    values.add((String)cb.getSelectionModel().getSelectedItem());
                } else {
                    values.add((String) column.getCellObservableValue(row).getValue().toString());
                }
            }
        }
        return values;
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

    @FXML
    private void onResidentClickedHandler(MouseEvent event) {
        selectedUser = residentListView.getSelectionModel().getSelectedItem(); //finds the selected item  

        diary.setResidentName(selectedUser);
        if (selectedUser != null) {
            ListOfDiaryNote.scrollTo(obList.size());
            readFiles();
        }
        //tag en string, RESIDENT NAME og brug den i en metode i diarys argument
        //loadUserNotes()
    }

    @FXML
    private void HomeBtnHandler(ActionEvent event) {
        sh.setNewScene("/GUI/FXML/Menu.fxml");
    }

    @FXML
    private void handleMedicineButtonAction(ActionEvent event) {
        sh.setNewScene("GUI/FXML/MedicinNote.fxml");
    }

    private void loadDosis() {
        String[] dosis = {"5 mg", "10 mg"};
        String a = "5 mg";
        String b = "10 mg";
        String c = "20 mg";
        String d = "30 mg";
        String e = "60 mg";
        String f = "90 mg";
        String g = "100 mg";
        String h = "150 mg";
        String i = "200 mg";
        String j = "0 mg";
        choiceList.addAll(j, a, b, c, d, e, f, g, h, i);
    }
}
