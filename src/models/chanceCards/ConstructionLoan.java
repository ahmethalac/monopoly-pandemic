package models.chanceCards;

import models.Card;
import models.Game;

public class ConstructionLoan implements Card {

    @Override
    public String getCardString() {
        return "Your construction loan has been approved get $15000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(15000);
    }
}
