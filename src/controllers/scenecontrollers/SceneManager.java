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

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void showMainMenuScene(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/MainMenuScene.fxml"));
            stage.setScene(new Scene(parent, width, height));
            stage.setTitle("MAIN MENU");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateGameScene(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/CreateGameScene.fxml"));
            stage.setScene(new Scene(parent, width, height));
            stage.setTitle("NEW GAME");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoadGameScene(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/LoadGameScene.fxml"));
            stage.setScene(new Scene(parent, width, height));
            stage.setTitle("LOAD GAME");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGameScene(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../views/sceneviews/GameScene.fxml"));
            Scene scene = new Scene(parent, width, height);
            stage.setScene(scene);
            stage.setTitle("MONOPOLY");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitGame() {
        stage.close();
    }
}
