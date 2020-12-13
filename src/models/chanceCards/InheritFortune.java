package models.chanceCards;

import models.Card;
import models.Game;

public class InheritFortune implements Card {
    // You inherit a fortune of $10000
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(10000);
    }
}
