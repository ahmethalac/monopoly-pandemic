package controllers.scenecontrollers;

import controllers.popupControllers.addPlayerPopupController;
import controllers.popupControllers.cityPopupController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraScene = new TableController();
        stackPane.getChildren().add(cameraScene);
        cameraScene.toBack();
    }

    //For debug purposes
    public void rotateTable(ActionEvent actionEvent) {
        cameraScene.rotateTable();
    }

    public void handleCityPopup() {

        //debug
        City city = new City(12.123, new double[]{100.0,200.0,300.0}, "Yozgat", 111);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/popupViews/cityPopup.fxml"));
            Parent parent = fxmlLoader.load();
            cityPopupController popupController = fxmlLoader.getController();
            popupController.setCity(city);

            Scene scene = new Scene(parent, 300, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
