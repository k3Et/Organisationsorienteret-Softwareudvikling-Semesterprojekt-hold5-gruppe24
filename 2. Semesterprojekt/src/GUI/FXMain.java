package GUI;

import Domain.Controller;
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