package models.chanceCards;

import models.Card;
import models.Game;

public class GoToQuarantine implements Card {

    @Override
    public String getCardString() {
        return "Go to quarantine region and stay there for 3 turns";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().quarantine(true);
    }
}
