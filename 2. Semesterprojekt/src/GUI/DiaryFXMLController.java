package GUI;

import Domain.Controller;
import Data.DatabaseHandler;
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
 * @author Sebastian, Rami, Benjamin, Patrick
 */
public class DiaryFXMLController implements Initializable {

    @FXML
    private TextArea WriteDiaryNote;
    @FXML
    private ListView<TextArea> ListOfDiaryNote;
    @FXML
    private ListView<User> residentListView;
    @FXML
    private TableColumn<Medicine, String> MedicineCol;
    @FXML
    private TableColumn<TableGetterSetter, ChoiceBox> DosisCol;
    @FXML
    private TableView<TableGetterSetter> tableView;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button saveEditing;

    private ObservableList<TextArea> obList;
    private ObservableList<User> obResidents;

    private User selectedUser;

    private SceneHandler sh = new SceneHandler();

    private User lastUser;

    private ObservableList<TableGetterSetter> data = FXCollections.observableArrayList();
    private ObservableList<String> choiceList = FXCollections.observableArrayList();
    private MedicineList medicineList = new MedicineList();

    private String selectedNoteDate;

    private TextArea textAreaNote;

    private String noteHolder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obList = FXCollections.observableArrayList();
        ListOfDiaryNote.setItems(obList);
        obResidents = FXCollections.observableArrayList();
        residentListView.setItems(obResidents);

        obResidents.addAll(LoginFXMLController.currentUserLoggedIn.getRelations());

        ListOfDiaryNote.setEditable(false);

        editBtn.setVisible(false);
        deleteBtn.setVisible(false);

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

        //Making saveEdit invisible until edit occurs.
        saveEditing.setVisible(false);
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
        String combines = writtenNote + "\n" + "Skrevet af: " + user.getName() + "\n" + DatabaseHandler.convertDate();

        if (!writtenNote.equals("")) {
            if (selectedUser == null) {
                TextArea warning = new TextArea("Vælg venligst en beboer");
                warning.setStyle("-fx-background-color: red;");
                warning.setStyle("-fx-font-style: Bold");
                obList.add(warning);
            } else {
                Controller.saveNote(user.getName(), selectedUser, combines, DatabaseHandler.convertDate());
                WriteDiaryNote.setText("");
                readResidentNoteFromDatabase();
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

    private void readEmployeNoteFromDatabase() {
        obList.clear();
        String fullDate = DatabaseHandler.convertDate();
        String date = fullDate.substring(0, 2);
        User user = LoginFXMLController.currentUserLoggedIn;
        for (String s : DatabaseHandler.getEmployeeNote(user.getName(), date)) {
            TextArea note = new TextArea(s);
            note.setEditable(false);
            note.setStyle("-fx-background-color: lightblue;");
            note.setWrapText(true);
            obList.add(note);
        }
        ListOfDiaryNote.scrollTo(obList.size());

    }

    private void readResidentNoteFromDatabase() {
        obList.clear();
        String fullDate = DatabaseHandler.convertDate();
        String date = fullDate.substring(0, 2);
        String user = selectedUser.getName();
        for (String s : DatabaseHandler.getResidentNote(user, date)) {
            textAreaNote = new TextArea(s);
            textAreaNote.setOnMouseClicked((e) -> {
                editBtn.setVisible(true);
                deleteBtn.setVisible(true);
                System.out.println("TextArea clicked");
                String selectedNote = textAreaNote.getText();
                selectedNoteDate = selectedNote.substring(selectedNote.length() - 19);
                noteHolder = selectedNote;
            });
            textAreaNote.setWrapText(true);
            textAreaNote.setEditable(false);
            textAreaNote.setStyle("-fx-background-color: lightblue;");
            obList.add(textAreaNote);
            ListOfDiaryNote.scrollTo(obList.size());
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
        if (selectedUser != null) {
            ListOfDiaryNote.scrollTo(obList.size());
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

    @FXML
    private void editBtnHandler(ActionEvent event) {

        saveEditing.setVisible(true);
        //Setting text in the writingfield so that it can be rewritten/edited.
        WriteDiaryNote.setText(noteHolder);

        //These buttons aren't supposed to be used at this time.
        deleteBtn.setVisible(false);
        editBtn.setVisible(false);
    }

    @FXML
    private void deleteBtnHandler(ActionEvent event) {
        System.out.println("date: " + selectedNoteDate);
        DatabaseHandler.deleteNote(selectedNoteDate);
        deleteBtn.setVisible(false);
        editBtn.setVisible(false);
        readResidentNoteFromDatabase();
    }

    @FXML
    private void handleSaveEditAction(ActionEvent event) {

        //Can't be used at this time.
        saveEditing.setVisible(false);

        //Editing the note in the database. 
        Controller.editNote(selectedNoteDate, WriteDiaryNote.getText());

        readResidentNoteFromDatabase();

        WriteDiaryNote.clear();

        deleteBtn.setVisible(true);
        editBtn.setVisible(true);
    }

}