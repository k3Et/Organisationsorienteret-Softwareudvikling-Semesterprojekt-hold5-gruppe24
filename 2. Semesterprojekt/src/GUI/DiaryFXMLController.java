package GUI;

import Domain.Controller;
import Domain.DatabaseHandler;
import Domain.Diary;
import Domain.ListOfResidents;
import Domain.Medicine;
import Domain.MedicineList;
import Domain.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    private User lastUser;

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

        readEmployeNoteFromDatabase();

        loadDosis();
        System.out.println(medicineList.getMedicineList().size());
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
        saveNoteInDatabase();
    }

    private void saveNoteInDatabase() {

        String writtenNote = formatMedicine(getTableViewValues());
        writtenNote += WriteDiaryNote.getText();
        User user = LoginFXMLController.currentUserLoggedIn;
        String combines = writtenNote + "\n" + "Skrevet af: " + user.getName()+"\n"+DatabaseHandler.convertDate();

        // d.setResidentName(selectedUser);
        if (!writtenNote.equals("")) {
            if (selectedUser == null) {
                TextArea warning = new TextArea("Vælg venligst en beboer");
                warning.setStyle("-fx-background-color: red;");
                warning.setStyle("-fx-font-style: Bold");
                obList.add(warning);
            } else {

                Controller.saveNote(user.getName(), selectedUser, combines);

                WriteDiaryNote.setText("");
            }
        }
    }

    //Returns array where all unequal indexes contain medicine names and equal indexes contain dosis. Medicine will not get added if they are equal to 0 mg.
    private List<String> getTableViewValues() {
        ArrayList<String> values = new ArrayList<>();
        ObservableList<TableColumn<TableGetterSetter, ?>> columns = tableView.getColumns();

        for (Object row : tableView.getItems()) {
            for (TableColumn column : columns) {
                if (column.getCellObservableValue(row).getValue() instanceof ChoiceBox) {
                    ChoiceBox cb = (ChoiceBox) column.getCellObservableValue(row).getValue();
                    if (cb.getSelectionModel().getSelectedItem().equals("0 mg")) {
                        values.remove(values.size() - 1);
                    } else {
                        values.add((String) cb.getSelectionModel().getSelectedItem());
                    }
                } else {
                    values.add((String) column.getCellObservableValue(row).getValue().toString());
                }
            }
        }
        return values;
    }

    private String formatMedicine(List<String> tempList) {
        String medString = "";
        if (!tempList.isEmpty()) {
            medString = "Medicin givet til beboer:\n";
            for (int i = 0; i < tempList.size(); i += 2) {
                medString += tempList.get(i) + ":\t" + tempList.get(i + 1) + "\n";
            }
            medString += "\n";
        }
        cleanChoiceBox();
        return medString;
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
        String fullDate = DatabaseHandler.convertDate();
        String date = fullDate.substring(0, 2);
        User user = LoginFXMLController.currentUserLoggedIn;
        for (String s : DatabaseHandler.getEmployeeNote(user.getName(), date)) {
            TextArea note = new TextArea(s);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
            System.out.println("yolo");
        }

    }

    private void readResidentNoteFromDatabase() {
        obList.clear();
        String fullDate = DatabaseHandler.convertDate();
        String date = fullDate.substring(0, 2);
        String user = selectedUser.getName();
        for (String s : DatabaseHandler.getResidentNote(user, date)) {
            TextArea note = new TextArea(s);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            obList.add(note);
            System.out.println("yolo");
        }
    }

    private void cleanChoiceBox() {
        for (TableGetterSetter tgs : data) {
            tgs.getChoiceBox().getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void onResidentClickedHandler(MouseEvent event) {
        if (selectedUser != null) {
            lastUser = selectedUser;
        }
        selectedUser = residentListView.getSelectionModel().getSelectedItem(); //finds the selected item  
        openInfo();
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

    private void handleMedicineButtonAction(ActionEvent event) {
        sh.setNewScene("GUI/FXML/MedicinNote.fxml");
    }

    private void loadDosis() {
        String[] dosis = {"0 mg", "5 mg", "10 mg", "20 mg", "30 mg", "60 mg", "90 mg", "100 mg", "150 mg", "200 mg"};
        choiceList.addAll(dosis);
    }

    @FXML
    private void infoOnResident(ActionEvent event) {
        if (selectedUser != null) {
            lastUser = selectedUser;
            openInfo();
        }
    }

    private void openInfo() {
        if (lastUser != null && lastUser.equals(selectedUser)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(DiaryFXMLController.class.getResource("/GUI/FXML/ResidentInformation.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Borger");
                stage.setScene(new Scene(root));
                stage.show();

                ResidentInformationController controller = loader.getController();
                controller.setNameField(selectedUser.getName());
                controller.setCprField(selectedUser.getCPR());
                controller.setPhoneField(selectedUser.getPhoneNumber());
                controller.setEmailField(selectedUser.getEmail());
                controller.setAdresseField(selectedUser.getAddress());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
