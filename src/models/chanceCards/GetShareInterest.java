package models.chanceCards;

import models.Card;
import models.Game;

public class GetShareInterest implements Card {

    @Override
    public String getCardString() {
        return "Get your shares interest : $2500";
    }

    @Override
    public void executeAction(Game game) {
        game.getCurrentPlayer().addMoney(2500);
    }
}
