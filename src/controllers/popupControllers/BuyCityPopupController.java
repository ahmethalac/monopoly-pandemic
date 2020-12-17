package controllers.popupControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import models.City;
import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.Player;
import utils.Sleeper;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class BuyCityPopupController extends PopupController implements Initializable {
    private City city;

    @FXML
    private Label cityNameLabel;
    @FXML
    private Label cityPriceLabel;
    @FXML
    public ListView<BorderPane> rentView;
    @FXML
    private Label buyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCity();
    }
    public void setCity() {
        this.city = (City) GameManager.getInstance().getCurrentRegion();
        cityNameLabel.setText(city.getName());
        cityPriceLabel.setText("Price: " + city.getPrice());
        BorderPane[] rentBoxList = new BorderPane[6];
        double[] rents = city.getBuildings().getRents();
        Label label0 = new Label("Site only ");
        Label label1 = new Label("1 House ");
        Label label2 = new Label("2 Houses ");
        Label label3 = new Label("3 Houses ");
        Label label4 = new Label("4 Houses ");
        Label label5 = new Label("Hotel ");

        for (int i = 0; i < 6; i++) {
            rentBoxList[i] = new BorderPane();
            rentBoxList[i].setRight(new Label("" + rents[i]));
        }

        rentBoxList[0].setLeft(label0);
        rentBoxList[1].setLeft(label1);
        rentBoxList[2].setLeft(label2);
        rentBoxList[3].setLeft(label3);
        rentBoxList[4].setLeft(label4);
        rentBoxList[5].setLeft(label5);

        ObservableList<BorderPane> rentList = FXCollections.observableArrayList(rentBoxList);
        rentView.setItems(rentList);

        // make listview unclickable
        rentView.setMouseTransparent(true);
        rentView.setFocusTraversable(false);
    }

    public void buyCityButton(ActionEvent event){
        // TODO write to Game Console
        if(GameManager.getInstance().buyCurrentLocation()){
            buyLabel.setText("City is bought");
        }
        else{
            buyLabel.setText("Your money is not enough to buy this city");
        }

        // wait for 1 sec
        Sleeper.sleep(1);

        closeButtonClicked(event);
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
