package models.chanceCards;

import models.Card;
import models.Game;

public class TaxRefund implements Card {
    @Override
    public String getCardString() {
        return "Tax refund take $2000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(2000);
    }
}
