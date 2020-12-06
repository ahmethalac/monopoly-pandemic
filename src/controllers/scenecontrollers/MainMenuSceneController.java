package controllers.scenecontrollers;

import javafx.event.ActionEvent;

public class MainMenuSceneController {
    public void handleNewGameButton(ActionEvent actionEvent){
        SceneManager.getInstance().showCreateGameScene();
    }

    public void handleLoadGameButton(ActionEvent actionEvent){
        SceneManager.getInstance().showLoadGameScene();
    }

    public void handleExitButton(ActionEvent actionEvent) {
        SceneManager.getInstance().exitGame();
    }

}
