package controllers.scenecontrollers;

import controllers.modelcontrollers.GameManager;
import controllers.popupControllers.AddPlayerPopupController;
import javafx.animation.PauseTransition;
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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Player;
import utils.ColorUtil;
import views.customJavaFXObjects.PlayerButton;
import views.customJavaFXObjects.PlayerComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static utils.ColorUtil.getFXColor;

/**
 * Controller for Create Game menu
 */
public class CreateGameSceneController implements Initializable {

    @FXML
    public Label errorLabel;
    @FXML
    private ListView<PlayerHBoxCell> listView;

    private static final ObservableList<PlayerHBoxCell> playerList = FXCollections.observableArrayList();

    public void displayErrorMessage(String msg){
        errorLabel.setText(msg);
        errorLabel.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> errorLabel.setVisible(false));
        delay.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setStyle("-fx-control-inner-background: #000a15; -fx-background-insets: 0 ; -fx-background-radius: 20;");
        listView.setItems(playerList);
    }

    /**
     * A method to handle start game button
     */
    public void handleStartGameButton() {
        if (playerList.size() < 1) {
            displayErrorMessage("Please add at least 2 players");
        } else {
            ArrayList<Player> players = new ArrayList<>();
            for (PlayerHBoxCell cell : playerList) {
                players.add(cell.getPlayer());
            }
            GameManager.getInstance().initGame(players);
            SceneManager.getInstance().showGameScene();
        }
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
            if (playerList.size() == 8){
                displayErrorMessage("You can add maximum 8 players");
            }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/AddPlayerPopup.fxml"));
                Parent parent = fxmlLoader.load();
                AddPlayerPopupController popupController = fxmlLoader.getController();
                popupController.setObservableList(playerList);

                Scene scene = new Scene(parent, 300, 200);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PlayerHBoxCell extends HBox {
        ImageView avatar = new ImageView();
        Label nameLabel = new Label();

        //debug
        Label idLabel = new Label();

        PlayerComboBox colorBox;
        Button avatarButton = new Button();
        PlayerButton deleteButton;

        Player player;

        // Constructor
        public PlayerHBoxCell(Player player) {
            super();

            this.player = player;

            this.setMaxHeight(40);
            this.setPrefHeight(40);


            Color playerColor = getFXColor(player.getColor());
            this.setBackground(new Background(new BackgroundFill(playerColor, new CornerRadii(25), Insets.EMPTY)));


            nameLabel.setText(player.getName());
            nameLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(nameLabel, Priority.ALWAYS);
            nameLabel.setFont(new Font(32));
            setMargin(nameLabel, new Insets(0, 0, 0, 20));

            //debug
            idLabel.setText(String.valueOf(player.getId()));
            idLabel.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(idLabel, Priority.ALWAYS);
            idLabel.setFont(new Font(32));

            colorBox = new PlayerComboBox(FXCollections.observableArrayList(ColorUtil.colors), player);
            colorBox.getSelectionModel().select(player.getColor());
            colorBox.setOnAction(actionEvent -> {
                String colorValue = colorBox.getValue();
                boolean alreadyExists = false;

                for (PlayerHBoxCell cell : playerList) {
                    if (cell.getPlayer().getColor().equals(colorValue)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (!alreadyExists) {
                    colorBox.getPlayer().setColor(colorValue);
                    Color color = getFXColor(colorValue);
                    this.setBackground(new Background(new BackgroundFill(color, new CornerRadii(25), Insets.EMPTY)));
                }
            });
            colorBox.setMaxHeight(39);
            colorBox.setPrefHeight(39);

            avatarButton.setText("Choose avatar");
            avatarButton.setMaxHeight(39);
            avatarButton.setPrefHeight(39);

            deleteButton = new PlayerButton(player, "Delete Player");
            deleteButton.setMaxHeight(39);
            deleteButton.setPrefHeight(39);
            setMargin(deleteButton, new Insets(0, 20, 0, 0));

            deleteButton.setOnAction(actionEvent -> {
                Object node = actionEvent.getSource();
                System.out.println(node instanceof Button);

                assert node instanceof PlayerButton;

                String playerName = ((PlayerButton) node).getPlayer().getName();

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
}

