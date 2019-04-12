package GUI;

import Domain.ListOfUsers;
import Domain.Role;
import Domain.Roles.Employee;
import Domain.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gruppe 24
 */
public class FXMain extends Application {

    private SceneHandler sh = new SceneHandler();

    public FXMain() {

    }

    private Stage primaryStage;


    @Override
    public void start(Stage stage) {

        primaryStage = stage;
        sh.setCurrentStage(primaryStage);
        sh.setNewScene("/GUI/FXML/Diary.fxml");
     
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
