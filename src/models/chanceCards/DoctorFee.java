package models.chanceCards;

import models.Card;
import models.Game;

public class DoctorFee implements Card {
    // Pay $5000 to your doctor
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(5000);
    }
}
