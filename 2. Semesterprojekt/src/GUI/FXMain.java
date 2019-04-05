package GUI;

import Domain.ListOfUsers;
import Domain.Role;
import Domain.Roles.Employee;
import Domain.User;
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

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/LoginFXML.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/AssignRole.fxml"));


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("NoTiBo - Noter til Dagbøger");

        stage.setMaximized(true)
        // stage.setMaximized(true);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

}
