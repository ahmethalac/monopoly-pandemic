package models.chanceCards;

import models.Card;
import models.Game;

public class BeautyContest implements Card {
    // you win the beauty contest take $1000
    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(1000);
    }
}
