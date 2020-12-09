package controllers.scenecontrollers;

import controllers.popupControllers.addPlayerPopupController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.models.Game;
import storage.models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for Create Game menu
 */
public class CreateGameSceneController implements Initializable {

    @FXML
    private ListView<PlayerHBoxCell> listView;

    private static final ObservableList<PlayerHBoxCell> playerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setStyle("-fx-control-inner-background: #000a15; -fx-background-insets: 0 ;");
        listView.setItems(playerList);
    }

    /**
     * A method to handle start game button
     */
    public void handleStartGameButton() {
        ArrayList<Player> players = new ArrayList<>();
        for (PlayerHBoxCell cell : playerList) {
            players.add(cell.getPlayer());
        }
        System.out.println(players);
        Game.getInstance().setPlayers(players);
        SceneManager.getInstance().showGameScene();
    }

    /**
     * A method to handle exit button
     */
    public void handleCreateGameExitButton() {
        SceneManager.getInstance().showMainMenuScene();
    }

    /**
     * A method to handle add player button
     */
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
        Label nameLabel = new Label();

        //debug
        Label idLabel = new Label();

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

        // Constructor
        public PlayerHBoxCell(Player player) {
            super();

            this.player = player;

            Color playerColor = getFXColor(player.getColor());
            this.setBackground(new Background(new BackgroundFill(playerColor, CornerRadii.EMPTY, Insets.EMPTY)));

            nameLabel.setText(player.getName());
            nameLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(nameLabel, Priority.ALWAYS);

            //debug
            idLabel.setText(String.valueOf(player.getId()));
            idLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(idLabel, Priority.ALWAYS);

            colorBox = new playerComboBox(FXCollections.observableArrayList(colors), player);
            colorBox.getSelectionModel().select(player.getColor());
            colorBox.setOnAction(actionEvent -> {
                String colorValue = colorBox.getValue();
                colorBox.getPlayer().setColor(colorValue);

                Color color = getFXColor(colorValue);

                this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
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

            this.getChildren().addAll(avatar, nameLabel, idLabel, avatarButton, colorBox, deleteButton);
        }
        public Player getPlayer() {
            return player;
        }

    }

    public static Color getFXColor(String colorString){
        return switch (colorString) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "pink" -> Color.PINK;
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            case "orange" -> Color.ORANGE;
            case "purple" -> Color.PURPLE;
            case "cyan" -> Color.CYAN;
            case "grey" -> Color.GREY;
            case "brown" -> Color.BROWN;
            default -> Color.BLACK;
        };
    }
}

