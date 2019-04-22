package GUI;

import Domain.Controller;
import Domain.ListOfUsers;
import Domain.Role;
import Domain.Roles.Employee;
import Domain.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Gruppe 24
 */
public class FXMain extends Application {

    SceneHandler sh = new SceneHandler();

    public FXMain() {

    }

    private Stage primaryStage;
    protected static Controller c = new Controller();
    @Override
    public void start(Stage stage) {


        Controller.setStockUsers();
        Controller.setStockRoleList();
        primaryStage = stage;
        sh.setCurrentStage(primaryStage);
        sh.setNewScene("/GUI/FXML/LoginFXML.fxml");
        stage.setTitle("NoTiBo - Noter til Dagbøger");
        stage.setMaximized(true);
       

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
