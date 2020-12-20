package models.chanceCards;

import controllers.modelcontrollers.GameManager;
import models.Card;
import models.Game;

public class GetInfection implements Card {
    @Override
    public String getCardString() {
        return "YOU ARE INFECTED, TAKE CARE";
    }

    @Override
    public void executeAction(Game game) {
        GameManager.getInstance().getCurrentPlayer().infect(true);
    }
}
