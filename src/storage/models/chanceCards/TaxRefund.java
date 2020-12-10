package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class TaxRefund implements Card {
    // tax refund take $2000
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(2000);
    }
}
