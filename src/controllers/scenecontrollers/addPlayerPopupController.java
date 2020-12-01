package controllers.scenecontrollers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Custom controller for add player popup dialog
 */
public class addPlayerPopupController {

    @FXML
    private TextField playerName;

    private ObservableList<CreateGameSceneController.HBoxCell> playerList;

    @FXML
    void addButtonClicked(ActionEvent event) {
        String name = playerName.getText().trim();

        playerList.add(new CreateGameSceneController.HBoxCell(name));

        closeStage(event);
    }

    public void setObservableList(ObservableList<CreateGameSceneController.HBoxCell> playerList) {
        this.playerList = playerList;
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}