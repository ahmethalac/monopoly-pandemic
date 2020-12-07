package controllers.scenecontrollers;

import controllers.popupControllers.addPlayerPopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Create Game menu
 */
public class CreateGameSceneController implements Initializable {

    private static final ObservableList<PlayerHBoxCell> playerList = FXCollections.observableArrayList();

    public void handleStartGameButton() {
        SceneManager.getInstance().showGameScene();
    }

    public void handleCreateGameExitButton() {
        SceneManager.getInstance().showMainMenuScene();
    }

    public void handleAddPlayerButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/addPlayerPopup.fxml"));
            Parent parent = fxmlLoader.load();
            addPlayerPopupController popupController = fxmlLoader.getController();
            popupController.setObservableList(playerList);

            Scene scene = new Scene(parent, 300, 200);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class PlayerHBoxCell extends HBox {
        ImageView avatar = new ImageView();
        Label label = new Label();

        //debug
        Label label3 = new Label();

        playerComboBox colorBox;
        Button avatarButton = new Button();
        playerButton deleteButton;

        Player player;

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

        public PlayerHBoxCell(Player player) {
            super();

            this.player = player;

            label.setText(player.getName());
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            //debug
            label3.setText(String.valueOf(player.getId()));
            label3.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label3, Priority.ALWAYS);

            colorBox = new playerComboBox(FXCollections.observableArrayList(colors), player);
            colorBox.getSelectionModel().select(player.getColor());
            colorBox.setOnAction(actionEvent -> {
                System.out.println(colorBox.getPlayer().getColor());
                colorBox.getPlayer().setColor(colorBox.getValue());
                System.out.println(colorBox.getPlayer().getColor());
            });

            avatarButton.setText("Choose avatar");

            deleteButton = new playerButton(player, "delete");

            deleteButton.setOnAction(actionEvent -> {
                Object node = actionEvent.getSource();
                System.out.println(node instanceof Button);
                assert node instanceof playerButton;
                playerButton b = (playerButton) node;
                String playerName = b.getPlayer().getName();

                PlayerHBoxCell cell;
                for (PlayerHBoxCell playerHBoxCell : playerList) {
                    if (playerName.equals(playerHBoxCell.getPlayer().getName())) {
                        cell = playerHBoxCell;
                        playerList.remove(cell);
                        break;
                    }
                }
            });

            this.getChildren().addAll(avatar, label, label3, avatarButton, colorBox, deleteButton);
        }

        public Player getPlayer() {
            return player;
        }
    }

    @FXML
    private ListView<PlayerHBoxCell> lView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lView.setStyle("-fx-control-inner-background: #000a15; -fx-background-insets: 0 ;");
        lView.setItems(playerList);
    }
}

