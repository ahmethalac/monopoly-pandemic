package controllers.scenecontrollers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class CreateGameSceneController {
    /**
     *  TODO
     *  Statik Players array eklenecek
     *  butonlar
     */

    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().showMainMenuScene();
    }
}

