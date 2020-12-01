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

    private ObservableList<CreateGameSceneController.PlayerHBoxCell> playerList;

    @FXML
    private TextField nameField;

    @FXML
    void addButtonClicked(ActionEvent event) {
        String name = nameField.getText().trim();

        System.out.println(getColor());
        Player player = new Player(name, getColor(), "pawn", getId());

        playerList.add(new CreateGameSceneController.PlayerHBoxCell(player));

        closeStage(event);
    }

    public void setObservableList(ObservableList<CreateGameSceneController.PlayerHBoxCell> playerList) {
        this.playerList = playerList;
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * TODO inspect code
     * @return
     */
    public int getId() {
        if ( playerList.size() == 0 ) return 0;

        for (int i = 0; i < playerList.size() + 1; i++) {
            for (int j = 0; j < playerList.size(); j++) {
                if (i == playerList.get(j).getPlayer().getId()) break;
                else if (j == playerList.size() - 1 ) return i;
            }
        }

        return playerList.size() - 1;
    }

    /**
     * TODO inspect code
     * @return
     */
    public String getColor() {
        if ( playerList.size() == 0 ) return colors[0];

        for (int i = 0; i < playerList.size() + 1; i++) {
            for (int j = 0; j < playerList.size(); j++) {
                if (i == playerList.get(j).getPlayer().getId()) break;
                else if (j == playerList.size() - 1 ) return colors[i];
            }
        }

        return colors[playerList.size() - 1];
    }
}