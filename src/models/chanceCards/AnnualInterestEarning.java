package models.chanceCards;

import models.Card;
import models.Game;

public class AnnualInterestEarning implements Card {
    @Override
    public String getCardString() {
        return "Annual interest earnings get $10000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(10000);
    }
}
