package controllers.popupControllers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import storage.models.Player;

/**
 * Custom controller for add player popup dialog
 */
public class addPlayerPopupController {

    final String[] colors = new String[]{
            "red",
            "blue",
            "pink",
            "green",
            "yellow",
            "orange",
            "purple",
            "cyan",
            "grey",
            "brown"};

    private ObservableList<CreateGameSceneController.HBoxCell> playerList;

    @FXML
    private TextField nameField;

    @FXML
    void addButtonClicked(ActionEvent event) {
        String name = nameField.getText().trim();

        Player player = new Player(name, "color", "pawn", 0);

        playerList.add(new CreateGameSceneController.HBoxCell(player));

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