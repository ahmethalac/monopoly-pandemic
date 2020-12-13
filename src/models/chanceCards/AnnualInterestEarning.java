package models.chanceCards;

import models.Card;
import models.Game;

public class AnnualInterestEarning implements Card {
    //Annual interest earnings get $10000
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(10000);
    }
}
