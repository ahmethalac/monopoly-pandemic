package controllers.popupControllers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.Player;
import utils.ColorUtil;

/**
 * Custom controller for add player popup dialog
 */
public class AddPlayerPopupController extends PopupController {

    private ObservableList<CreateGameSceneController.PlayerHBoxCell> playerList;

    @FXML
    public VBox addPlayerVBox;
    @FXML
    private TextField nameField;

    @FXML
    void onEnter(ActionEvent event) {
        addButtonClicked(event);
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        boolean playerAlreadyExists = false;

        String name = nameField.getText().trim();

        for (CreateGameSceneController.PlayerHBoxCell cell : playerList) {
            if (cell.getPlayer().getName().equals(name)) {
                playerAlreadyExists = true;
                break;
            }
        }

        if (playerAlreadyExists) {
            addPlayerVBox.getChildren().clear();
            addPlayerVBox.getChildren().addAll(new Label("Player already exists"));
        }
        else{
            Player player = new Player(name, getColor(), "pawn", getId());
            playerList.add(new CreateGameSceneController.PlayerHBoxCell(player));
            closeStage(event);
        }
    }



    public void setObservableList(ObservableList<CreateGameSceneController.PlayerHBoxCell> playerList) {
        this.playerList = playerList;
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
        if ( playerList.size() == 0 ) return ColorUtil.colors[0];

        for (int i = 0; i < playerList.size() + 1; i++) {
            for (int j = 0; j < playerList.size(); j++) {
                if (ColorUtil.colors[i].equals(playerList.get(j).getPlayer().getColor())) break;
                else if (j == playerList.size() - 1 ) return ColorUtil.colors[i];
            }
        }

        return ColorUtil.colors[playerList.size() - 1];
    }


}
