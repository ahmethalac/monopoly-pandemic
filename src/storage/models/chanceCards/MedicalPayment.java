package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class MedicalPayment implements Card {
    //Pay $5000 for medical payments
    @Override
    public void executeAction() {
        Game.getInstance().getCurrentPlayer().removeMoney(5000);
    }
}
