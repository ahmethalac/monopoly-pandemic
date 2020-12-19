package models.chanceCards;

import models.Card;
import models.Game;

public class RiddleContest implements Card {

    @Override
    public String getCardString() {
        return "You won a riddle contest get $10000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(10000);
    }
}
