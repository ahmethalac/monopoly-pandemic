package models.chanceCards;

import models.Card;
import models.Game;

public class MoveBackToLasVegas implements Card {

    @Override
    public String getCardString() {
        return "Go back to Las Vegas";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().setLocation(6);
    }
}
