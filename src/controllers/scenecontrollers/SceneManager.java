package controllers.scenecontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static SceneManager instance = null;
    private Stage stage;
    private final double height = 1080;
    private final double width = 1920;

    public static SceneManager getInstance(){
        if (instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void showMainMenuScene() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/MainMenuScene.fxml"));
        stage.setScene(new Scene(parent, width, height));
        stage.setTitle("MAIN MENU");
    }

    public void showCreateGameScene() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/CreateGameScene.fxml"));
        stage.setScene(new Scene(parent, width, height));
        stage.setTitle("NEW GAME");
    }

    public void showLoadGameScene() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/LoadGameScene.fxml"));
        stage.setScene(new Scene(parent, width, height));
        stage.setTitle("LOAD GAME");
    }

    public void exitGame() {
        stage.close();
    }
}
