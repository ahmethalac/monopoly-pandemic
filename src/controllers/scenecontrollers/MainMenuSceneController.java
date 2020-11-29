package controllers.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuSceneController {
    public void handleNewGameButton(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().showCreateGameScene();
    }

    public void handleLoadGameButton(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().showLoadGameScene();
    }

    public void handleExitButton(ActionEvent actionEvent) {
        SceneManager.getInstance().exitGame();
    }

}
