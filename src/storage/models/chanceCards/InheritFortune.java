package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class InheritFortune implements Card {
    // You inherit a fortune of $10000
    @Override
    public void executeAction() {
        Game.getInstance().getCurrentPlayer().addMoney(10000);
    }
}
