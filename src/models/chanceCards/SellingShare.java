package models.chanceCards;

import models.Card;
import models.Game;

public class SellingShare implements Card {
    // you sold your shares get $5000
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(5000);
    }
}
