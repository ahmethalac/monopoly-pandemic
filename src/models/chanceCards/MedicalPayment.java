package models.chanceCards;

import models.Card;
import models.Game;

public class MedicalPayment implements Card {
    //Pay $10000 for medical payments
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(10000);
    }
}
