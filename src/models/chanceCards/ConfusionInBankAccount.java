package models.chanceCards;

import models.Card;
import models.Game;

public class ConfusionInBankAccount implements Card{
    @Override
    public String getCardString() {
        return "There is a confusion in bank accounts, get $20000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(20000);
    }
}
