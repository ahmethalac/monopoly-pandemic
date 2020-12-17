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
    private City city;

    @FXML private ComboBox comboBox;
    @FXML private TextField textField;
    @FXML private Label buildingNumberLabel;
    @FXML private Label resultLabel;
    @FXML private Button sellButton;

    public void setPlayer(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPlayer();
        int listLength = player.getCities().size();
        for(int i = 0; i < listLength; i++) {
            comboBox.getItems().add(player.getCities().get(i).getName());
        }
        buildingNumberLabel.setText("");
        resultLabel.setText("");
        textField.setText("");
    }

    public void comboBoxUpdated()
    {
        String cityName = comboBox.getValue().toString();
        city = findCity(cityName);
        buildingNumberLabel.setText("There are " + city.getNumberOfBuildings() + " buildings on " + city.getName());
    }

    public void sellBuildings(){
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
            int numberOfBuildingsWantedToSell = Integer.parseInt(textField.getText());

            if(GameManager.getInstance().sellBuilding(city, numberOfBuildingsWantedToSell, player))
            {
                resultLabel.setText(numberOfBuildingsWantedToSell + " buildings are sold from " + city.getName());
                sellButton.setDisable(true);
            }
            else
            {
                resultLabel.setText("This operation cannot be done");
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
