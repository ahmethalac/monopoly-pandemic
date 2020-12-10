package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class ConfusionInBankAccount implements Card{
    // there is a confusion in bank accounts, get $20000
    @Override
    public void executeAction() {
        Game.getInstance().getCurrentPlayer().addMoney(20000);
    }
}
