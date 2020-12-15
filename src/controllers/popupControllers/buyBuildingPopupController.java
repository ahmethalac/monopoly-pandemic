package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import models.City;
import models.Player;

import java.util.ArrayList;

public class buyBuildingPopupController extends popupController{
    private Player player;

    @FXML
    public ListView<BorderPane> cityView;

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

        ObservableList<BorderPane> citylist = FXCollections.observableArrayList(cityList);
        cityView.setItems(citylist);
    }
    public void buyOneHouse(){

    }
}
