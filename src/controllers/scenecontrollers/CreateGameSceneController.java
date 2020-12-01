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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Create Game menu
 */
public class CreateGameSceneController implements Initializable {

    public static class HBoxCell extends HBox {
        ImageView avatar = new ImageView();
        Label label = new Label();
        Button colorButton = new Button();
        Button avatarButton = new Button();
        Button deleteButton = new Button();

        public HBoxCell(String playerName) {
            super();

            label.setText(playerName);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);


            colorButton.setText("Choose color");
            avatarButton.setText("Choose avatar");

            this.getChildren().addAll(avatar, label, colorButton, avatarButton, deleteButton);
        }
    }

    private final ObservableList<HBoxCell> playerList = FXCollections.observableArrayList();

    @FXML
    private ListView<HBoxCell> lView;

    @FXML
    private Button createGameExit;

    @FXML
    private Button addPlayerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addPlayerButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/addPlayerPopup.fxml"));
                Parent parent = fxmlLoader.load();
                addPlayerPopupController popupController = fxmlLoader.getController();
                popupController.setObservableList(playerList);

                Scene scene = new Scene(parent, 300, 200);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createGameExit.setOnAction(actionEvent -> {
            try {
                SceneManager.getInstance().showMainMenuScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        lView.setStyle("-fx-control-inner-background: #000a15; -fx-background-insets: 0 ;");

        for (int i = 0; i < 3; i++) {
            HBoxCell hbox = new HBoxCell("Item " + i);
            playerList.add(hbox);
        }
        lView.setItems(playerList);
    }
}

