package models.chanceCards;

import controllers.modelcontrollers.GameManager;
import models.Card;
import models.Game;

public class MoveToStart implements Card {
    @Override
    public String getCardString() {
        return "Move to Start";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().setLocation(0);
    }
}
