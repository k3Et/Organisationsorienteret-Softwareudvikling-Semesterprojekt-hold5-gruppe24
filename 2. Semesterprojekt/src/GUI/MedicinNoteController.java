/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Yourk
 */
public class MedicinNoteController implements Initializable {

    @FXML
    private TableColumn<?, ?> SelectCol;
    @FXML
    private TableColumn<?, ?> MedicineCol;
    @FXML
    private TableColumn<?, ?> DosisCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AcceptHandler(ActionEvent event) {
    }

    @FXML
    private void CancelHandler(ActionEvent event) {
    }
    
}
