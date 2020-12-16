package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.City;
import models.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class buyBuildingPopupController extends popupController implements Initializable {
    private Player player;

    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;
    @FXML private Button oneHouseButton;
    @FXML private Button twoHouseButton;
    @FXML private Button threeHouseButton;
    @FXML private Button fourHouseButton;
    @FXML private Button hotelButton;

    public void setPlayer(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        setPlayer();
        int listLength = player.getCities().size();
        for(int i = 0; i < listLength; i++)
        {
            comboBox.getItems().add(player.getCities().get(i).getName());
        }
        comboBoxLabel.setText("");
    }

    /* Finds the city from player's city array list
    * @param name of the city that is searched
    */
    public City findCity(String cityName){
        int listLength = player.getCities().size();
        int index = 0;
        for(int i = 0; i < listLength; i++)
        {
            if(cityName.equals(player.getCities().get(i).getName()))
            {
                index = i;
                break;
            }
        }
        return player.getCities().get(index);
    }

    // onclick method for oneHouse button
    public void oneHouse(){
        String cityName = comboBox.getValue().toString();
        City city = findCity(cityName);
        double costOfOneHouse = (city.getPrice()) / 4;
        if(player.getMoney() >= costOfOneHouse)
        {
            player.removeMoney(costOfOneHouse);
            city.addBuilding(1);
            comboBoxLabel.setText("One house is added to " + city.getName());
            // if the action is succesful player cannot click any more buttons
            disableButtons();
        }
        // else { not enough money }
        //player can click and try another method as long as he cannot buy a building
    }

    public void twoHouses(){
        String cityName = comboBox.getValue().toString();
        City city = findCity(cityName);
        double costOfTwoHouses = (city.getPrice()) / 2;
        if(player.getMoney() >= costOfTwoHouses)
        {
            player.removeMoney(costOfTwoHouses);
            city.addBuilding(2);
            comboBoxLabel.setText("Two house is added to " + city.getName());
            // if the action is succesful player cannot click any more buttons
            disableButtons();
        }
        // else { not enough money }
        //player can click and try another method as long as he cannot buy a building
    }

    public void threeHouses(){
        String cityName = comboBox.getValue().toString();
        City city = findCity(cityName);
        double costOfThreeHouses = (city.getPrice()) / 4 * 3;
        if(player.getMoney() >= costOfThreeHouses)
        {
            player.removeMoney(costOfThreeHouses);
            city.addBuilding(3);
            comboBoxLabel.setText("Three house is added to " + city.getName());
            // if the action is succesful player cannot click any more buttons
            disableButtons();
        }
        // else { not enough money }
        //player can click and try another method as long as he cannot buy a building
    }

    public void fourHouses(){
        String cityName = comboBox.getValue().toString();
        City city = findCity(cityName);
        double costOfFourHouses = city.getPrice();
        if(player.getMoney() >= costOfFourHouses)
        {
            player.removeMoney(costOfFourHouses);
            city.addBuilding(4);
            comboBoxLabel.setText("Four house is added to " + city.getName());
            // if the action is succesful player cannot click any more buttons
            disableButtons();
        }
        // else { not enough money }
        //player can click and try another method as long as he cannot buy a building
    }

    public void hotel(){
        String cityName = comboBox.getValue().toString();
        City city = findCity(cityName);
        double costOfHotel = (city.getPrice()) / 4 * 5;
        if(player.getMoney() >= costOfHotel)
        {
            player.removeMoney(costOfHotel);
            city.addBuilding(5);
            comboBoxLabel.setText("Hotel is added to " + city.getName());
            // if the action is succesful player cannot click any more buttons
            disableButtons();
        }
        // else { not enough money }
        //player can click and try another method as long as he cannot buy a building
    }

    public void disableButtons(){
        oneHouseButton.setDisable(true);
        twoHouseButton.setDisable(true);
        threeHouseButton.setDisable(true);
        fourHouseButton.setDisable(true);
        hotelButton.setDisable(true);
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
