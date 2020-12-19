package models.chanceCards;

import models.Card;
import models.Game;

public class HighSpeedPenalty implements Card {

    @Override
    public String getCardString() {
        return "You were driving fast, pay $1500 as penalty";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(1500);
    }
}
