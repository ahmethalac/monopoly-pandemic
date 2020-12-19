package models.chanceCards;

import models.Card;
import models.Game;

public class MoveToTokyo implements Card {

    @Override
    public String getCardString() {
        return "Move to Tokyo if you pass through start get $25000";
    }

    @Override
    public void executeAction(Game game) {
        if(game.getCurrentPlayer().getLocation() > 26)
        {
            game.getCurrentPlayer().addMoney(25000);
            game.getCurrentPlayer().setLocation(26);
        }
        else
        {
            game.getCurrentPlayer().setLocation(26);
        }
    }
}
