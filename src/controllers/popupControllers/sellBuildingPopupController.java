package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
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

public class sellBuildingPopupController implements Initializable {
    private Player player;

    @FXML private ComboBox cityComboBox;
    @FXML private TextField userInput;
    @FXML private Button sellButton;
    @FXML private Label buildingNumberLabel;
    @FXML private Label resultLabel;

    public void setPlayer(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int listLength = player.getCities().size();
        for(int i = 0; i < listLength; i++) {
            cityComboBox.getItems().add(player.getCities().get(i).getName());
        }
        buildingNumberLabel.setText("");
        resultLabel.setText("");
        userInput.setText("");
    }

    // This method prints the information, on popup, about number of buildings on the selected city
    public void showBuildingNumber()
    {
        String cityName = cityComboBox.getValue().toString();
        buildingNumberLabel.setText("There are " + findCity(cityName).getNumberOfBuildings() + " buildings on " + findCity(cityName).getName());
    }

    public void sellBuildings(){
        boolean isNumber = true;
        //First check whether user input is an integer
        if(userInput.getText().equals(""))
        {
            // no input is written, no operation
        }
        else
        {
            for(int i = 0; i < userInput.getText().length(); i++)
            {
                if(!isDigit(userInput.getText().charAt(i)))
                {
                    isNumber = false;
                }
            }
        }
        // now we know that input is integer
        if(isNumber)
        {
            // convert string to integer
            int numberOfBuildingsWantedToSell = Integer.parseInt(userInput.getText());

            String cityName = cityComboBox.getValue().toString();
            if(numberOfBuildingsWantedToSell > findCity(cityName).getNumberOfBuildings()) {
                // incorrect entry more buildings are tried to be sold
                resultLabel.setText("Your value is higher than total number of houses");
            }
            else {
                findCity(cityName).removeBuilding(numberOfBuildingsWantedToSell);
                player.addMoney(findCity(cityName).getPrice() / 4 * numberOfBuildingsWantedToSell);
                resultLabel.setText(numberOfBuildingsWantedToSell + " houses are removed from " + cityName);
                sellButton.setDisable(true);
            }
        }
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
}
