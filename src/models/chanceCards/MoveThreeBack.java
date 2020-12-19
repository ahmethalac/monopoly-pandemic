package models.chanceCards;

import models.Card;
import models.Game;

public class MoveThreeBack implements Card {

    @Override
    public String getCardString() {
        return "Move three region back";
    }

    @Override
    public void executeAction(Game game) {
        int currentLoc = game.getCurrentPlayer().getLocation();
        game.getCurrentPlayer().setLocation(currentLoc - 3);
    }
}
