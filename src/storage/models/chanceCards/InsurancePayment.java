package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class InsurancePayment implements Card {
    // Pay $5000 for insurance
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(5000);
    }
}
