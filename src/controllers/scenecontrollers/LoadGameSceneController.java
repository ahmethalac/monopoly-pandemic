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
        buttonBox.setPrefWidth(500);
        buttonBox.setPrefHeight(700);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setSpacing(15.0);
        DataManager dataManager = DataManager.getInstance();
        List<String> savedNames = dataManager.getSavedNames();
        for ( String saveName: savedNames ) {
            Button newButton = new Button(saveName);
            newButton.setStyle("-fx-background-color: #5b4b4b; -fx-text-fill: #ffffff; -fx-font-size: 2em");
            newButton.setMinWidth(buttonBox.getWidth());
            newButton.setPrefWidth(300);
            newButton.setPrefHeight(100);
            newButton.setMinHeight(100);
            newButton.setOnAction(this::handleLoadGameButton);
            buttonBox.getChildren().add(newButton);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: #000000; -fx-border-color: #000000;");
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.setPrefViewportWidth(150);
        scrollPane.setContent(buttonBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        loadBox.getChildren().add(scrollPane);
    }

    public void handleLoadGameButton(ActionEvent actionEvent) {
        DataManager.getInstance().loadGame(((Button)actionEvent.getSource()).getText());
        SceneManager.getInstance().showGameScene();
    }

    public void handleBackButton(ActionEvent actionEvent){
        SceneManager.getInstance().showMainMenuScene();
    }
}
