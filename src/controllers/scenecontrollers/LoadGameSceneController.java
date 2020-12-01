package controllers.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import storage.filemanager.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;


public class LoadGameSceneController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSavedGames();
    }

    @FXML
    private VBox loadBox;
    public void getSavedGames() {
        VBox buttonBox = new VBox();
        buttonBox.setMaxWidth(1500);
        buttonBox.setMaxHeight(700);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 50;");
        buttonBox.setSpacing(15.0);
        DataManager dataManager = DataManager.getInstance();
        List<String> savedNames = dataManager.getSavedNames();
        for ( String saveName: savedNames ) {
            Button newButton = new Button(saveName);
            newButton.setMinWidth(buttonBox.getWidth());
            newButton.setPrefWidth(300);
            newButton.setPrefHeight(100);
            buttonBox.getChildren().add(newButton);
        }
        loadBox.getChildren().add(buttonBox);
    }

    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        SceneManager.getInstance().showMainMenuScene();
    }
}
