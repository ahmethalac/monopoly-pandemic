package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.City;
import models.Player;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Character.isDigit;

public class SellBuildingPopupController extends PopupController implements Initializable {
    private Player player;

    @FXML private ComboBox cityComboBox;
    @FXML private TextField userInput;
    @FXML private Label buildingNumberLabel;
    @FXML private Label resultLabel;

    public void setPlayer(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPlayer();
        int listLength = player.getCities().size();
        for(int i = 0; i < listLength; i++) {
            cityComboBox.getItems().add(player.getCities().get(i).getName());
        }
        buildingNumberLabel.setText("");
        resultLabel.setText("");
        userInput = new TextField();
        userInput.setText("");
    }

    public void comboBoxUpdated()
    {
        String cityName = cityComboBox.getValue().toString();
        buildingNumberLabel.setText("There are " + findCity(cityName).getNumberOfBuildings() + " buildings on " + findCity(cityName).getName());
    }

    public void sellBuildings(){
        boolean isNumber = true;
        //First check whether user input is an integer
        if(!(userInput.getText().equals("")))
        {
            for(int i = 0; i < userInput.getText().length(); i++)
            {
                if(!isDigit(userInput.getText().charAt(i)))
                {
                    isNumber = false;
                }
            }
        }
        else
        {
            isNumber = false;
        }

        if(isNumber)
        {
            int numberOfBuildingsWantedToSell = Integer.parseInt(userInput.getText());

            String cityName = cityComboBox.getValue().toString();
            if(GameManager.getInstance().sellBuilding(findCity(cityName), numberOfBuildingsWantedToSell))
            {
                resultLabel.setText(numberOfBuildingsWantedToSell + " buildings are sold from " + cityName);
            }
        }
    }

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

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
