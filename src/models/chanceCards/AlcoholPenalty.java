package models.chanceCards;

import models.Card;
import models.Game;

public class AlcoholPenalty implements Card {

    @Override
    public String getCardString() {
        return "You were driving with high blood alcohol pay $2000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(2000);
    }
}
