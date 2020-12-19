package models.chanceCards;

import models.Card;
import models.Game;

public class BeautyContest implements Card {
    @Override
    public String getCardString() {
        return "You win the beauty contest get $1000";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(1000);
    }
}
