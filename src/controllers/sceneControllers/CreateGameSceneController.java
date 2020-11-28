package controllers.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

