package models.chanceCards;

import models.Card;
import models.Game;

public class DoctorFee implements Card {
    @Override
    public String getCardString() {
        return "You visited your doctor pay $5000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().removeMoney(5000);
    }
}
