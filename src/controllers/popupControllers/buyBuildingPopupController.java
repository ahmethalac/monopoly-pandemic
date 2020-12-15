package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.City;
import models.Player;

import java.util.ArrayList;

public class buyBuildingPopupController {
    Player player;

    @FXML
    public ListView<BorderPane> cityView;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    public buyBuildingPopupController(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    public void displayPlayersCities(){

        ArrayList<City> cities = player.getCities();

        BorderPane[] cityList = new BorderPane[cities.size()];

        for (int i = 0; i < cities.size(); i++) {
            cityList[i] = new BorderPane();
            cityList[i].setLeft(new Label("" + cities.get(i)));
        }

        ObservableList<BorderPane> rentList = FXCollections.observableArrayList(cityList);
        cityView.setItems(rentList);
    }
    public void buyOneHouse(){

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
