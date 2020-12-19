package models.chanceCards;

import models.Card;
import models.Game;

public class InsurancePayment implements Card {
    @Override
    public String getCardString() {
        return "Pay $5000 for insurance";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(5000);
    }
}
