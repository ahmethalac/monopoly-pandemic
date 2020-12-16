package controllers.scenecontrollers;

import controllers.modelcontrollers.GameManager;
import controllers.popupControllers.addPlayerPopupController;
import controllers.popupControllers.agreementPopupController;
import controllers.popupControllers.buyBuildingPopupController;
import controllers.popupControllers.cityPopupController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.City;
import models.Game;
import models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private TableController cameraScene;

    @FXML
    private StackPane stackPane;
    @FXML
    private HBox cardsBar;
    @FXML
    private Text money;
    @FXML
    private Text playerName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraScene = new TableController(this);
        stackPane.getChildren().add(cameraScene);
        cameraScene.toBack();
        GameManager.getInstance().setPlayerObservers(this);


        //test purpose
        //double[] rents = {1,2,3,4,5,6};
        //Player player1 = new Player("goktug", "blue", "car", 1);
        //City city1 = new City(100, rents, "ankara", 1);
        //City city2 = new City(100, rents, "istanbul", 2);
        //City city3 = new City(100, rents, "malata", 3);
        // player1.addCity(city1);
        //player1.addCity(city2);
        //player1.addCity(city3);
        //renderPlayer(player1);
    }

    //Update the game scene according to the current player
    public void renderPlayer(Player player){
        playerName.setText(player.getName());
        money.setText(Double.toString(player.getMoney()));
        cardsBar.setStyle("-fx-spacing: 10;");
        for(int i = 0; i < player.getCities().size(); i++){
            Button button = new Button(player.getCities().get(i).getName());
            int finalI = i;
            button.setStyle("-fx-border-color: black");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    handleCityPopup(player.getCities().get(finalI));
                }
            });

            cardsBar.setAlignment(Pos.CENTER);
            cardsBar.getChildren().add(button);
        }


    }

    //For debug purposes
    public void rotateTable(ActionEvent actionEvent) {
        cameraScene.rotateTable();
    }

    private void handlePopup(Parent parent) {
        Scene scene = new Scene(parent, 300, 400);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void handleCityPopup(City city) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/cityPopup.fxml"));
            Parent parent = fxmlLoader.load();
            cityPopupController popupController = fxmlLoader.getController();
            popupController.setCity(city);

            handlePopup(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAgreementPopup() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/agreementPopup.fxml"));
            Parent parent = fxmlLoader.load();
            handlePopup(parent);
    }

    public void handleBuyBuildingPopup() throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/buyBuildingPopup.fxml"));
            Parent parent = fxmlLoader.load();
            buyBuildingPopupController buyBuildingController = fxmlLoader.getController();
            buyBuildingController.setPlayer();

            handlePopup(parent);
    }

    public void handleSellBuildingPopup() throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/sellBuildingPopup.fxml"));
            Parent parent = fxmlLoader.load();

            handlePopup(parent);
    }
}
