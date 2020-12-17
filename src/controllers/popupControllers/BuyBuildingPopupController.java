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

public class BuyBuildingPopupController extends PopupController implements Initializable {
    private Player player;
    private City city;

    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;
    @FXML private Label resultLabel;
    @FXML private Button buyButton;
    @FXML private TextField textField;

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
        resultLabel.setText("");
        textField.setText("");
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

    public void comboBoxUpdated()
    {
        String cityName = comboBox.getValue().toString();
        city = findCity(cityName);
        comboBoxLabel.setText("There are already " + city.getNumberOfBuildings() + " buildings on " + city.getName());
    }

    public void buyBuildings(){
        boolean isNumber = true;
        if(!(textField.getText().equals("")))
        {
            for(int i = 0; i < textField.getText().length(); i++)
            {
                if(!isDigit(textField.getText().charAt(i)))
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
            int numberOfBuildingsWantedToBuy = Integer.parseInt(textField.getText());

            if((numberOfBuildingsWantedToBuy + city.getNumberOfBuildings() <= 5) &&
                    GameManager.getInstance().buyBuilding(city, numberOfBuildingsWantedToBuy, player))
            {
                resultLabel.setText(numberOfBuildingsWantedToBuy + " buildings are bought " + city.getName());
                buyButton.setDisable(true);
            }
            else
            {
                resultLabel.setText("Invalid number of cities or not enough money");
            }
        }
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
