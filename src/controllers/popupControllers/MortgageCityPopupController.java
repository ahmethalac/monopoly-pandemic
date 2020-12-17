package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.City;
import models.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class MortgageCityPopupController extends PopupController implements Initializable {
    private Player player;
    private City city;

    private ToggleGroup radioButtons;

    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;
    @FXML private Label resultLabel;
    @FXML private RadioButton mortgageRadioButton;
    @FXML private RadioButton liftMortgageRadioButton;
    @FXML private Button performButton;


    public void setPlayer(){
        player = GameManager.getInstance().getCurrentPlayer();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPlayer();
        radioButtons = new ToggleGroup();
        mortgageRadioButton.setToggleGroup(radioButtons);
        liftMortgageRadioButton.setToggleGroup(radioButtons);
        comboBoxLabel.setText("");
        resultLabel.setText("");
        performButton.setText("");
    }

    public void updateComboBoxUnMortgaged(){
        int listLength = player.getCities().size();
        comboBox.getItems().clear();
        for(int i = 0; i < listLength; i++)
        {
            if(!(player.getCities().get(i).isMortgaged())){
                comboBox.getItems().add(player.getCities().get(i).getName());
            }
        }
        comboBoxLabel.setText("");
        performButton.setText("Mortgage");
        resultLabel.setText("");
    }

    public void updateComboBoxMortgaged(){
        int listLength = player.getCities().size();
        comboBox.getItems().clear();
        for(int i = 0; i < listLength; i++)
        {
            if(player.getCities().get(i).isMortgaged()){
                comboBox.getItems().add(player.getCities().get(i).getName());
            }
        }
        comboBoxLabel.setText("");
        performButton.setText("Lift Mortgage");
        resultLabel.setText("");
    }

    public void comboBoxUpdated()
    {
        if(comboBox.getValue() != null) {
            String cityName = comboBox.getValue().toString();
            city = findCity(cityName);
            resultLabel.setText("");
            if (mortgageRadioButton.isSelected()) {
                comboBoxLabel.setText("The money you receive : " + city.getPrice() * 0.7);
            } else if (liftMortgageRadioButton.isSelected()) {
                comboBoxLabel.setText("The money you must pay to lift the mortgage :\n" + city.getPrice() * 0.8);
            }
        }
    }

    public void performSelectedAction(){
        if(comboBox.getValue() != null) {
            if (mortgageRadioButton.isSelected()) {
                GameManager.getInstance().mortgageCity(city, true);
                resultLabel.setText(city.getName() + " is mortgaged");
            } else {
                GameManager.getInstance().mortgageCity(city, false);
                resultLabel.setText("Mortgage is lifted from " + city.getName());
            }
        }
        else{
            resultLabel.setText("Please choose a city before action");
        }
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
