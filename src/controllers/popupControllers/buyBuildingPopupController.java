package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import models.Player;

public class buyBuildingPopupController {
    Player player;

    public buyBuildingPopupController(){
        player = GameManager.getInstance().getCurrentPlayer();
    }

    public void displayPlayersCities(){
        player.getCities();
    }
    public void buyOneHouse(){

    }
}
