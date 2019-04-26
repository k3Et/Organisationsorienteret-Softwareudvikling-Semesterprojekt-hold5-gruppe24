package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rami
 */
public class SceneHandler {

    public static Stage currentStage;
    public static Scene currentScene;
    private static String currentSceneString;

    public SceneHandler() {

    }

    public void setNewScene(String fxml) {

        try {
            System.out.println(fxml);

            Parent parent = FXMLLoader.load(getClass().getResource(fxml));

            currentScene = new Scene(parent);
            currentStage.setScene(currentScene);

            currentStage.show();

            matchCSSWithFXML(fxml);
            //currentSceneString = fxml;
        } catch (IOException ex) {
            Logger.getLogger(SceneHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void matchCSSWithFXML(String fxml) {
        if (fxml.equals("/GUI/FXML/Menu.fxml")) {
            String css = MenuFXMLController.class.getResource("Menu.css").toExternalForm();
            currentScene.getStylesheets().add(css);
        }
    }

    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

}