package controllers.scenecontrollers;

import controllers.modelcontrollers.GameManager;
import controllers.popupControllers.addPlayerPopupController;
import controllers.popupControllers.agreementPopupController;
import controllers.popupControllers.cityPopupController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Agreement;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraScene = new TableController(this);
        stackPane.getChildren().add(cameraScene);
        cameraScene.toBack();
        GameManager.getInstance().setPlayerObservers(this);

    }

    //For debug purposes
    public void rotateTable(ActionEvent actionEvent) {
        cameraScene.rotateTable();
    }

    public void renderPlayer(Player player){
        //TODO Render Game Scene with the information of player
    }

    public void handleCityPopup(City city) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/cityPopup.fxml"));
            Parent parent = fxmlLoader.load();
            cityPopupController popupController = fxmlLoader.getController();
            popupController.setCity(city);

            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleAgreementPopup(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/agreementPopup.fxml"));
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleBuyBuildingPopup(City city, Player player){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/buyBuildingPopup.fxml"));
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSellBuildingPopup(City city, Player player){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/sellBuildingPopup.fxml"));
            Parent parent = fxmlLoader.load();

            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
