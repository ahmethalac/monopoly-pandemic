package models.chanceCards;

import models.Card;
import models.Game;

public class MoveToStockholm implements Card {
    @Override
    public String getCardString() {
        return "Go to Stockholm. If you pass through start get $25000";
    }

    @Override
    public void executeAction(Game game) {
        if(game.getCurrentPlayer().getLocation() > 37){
            game.getCurrentPlayer().addMoney(25000);
            game.getCurrentPlayer().setLocation(37);
        }
        else{
            game.getCurrentPlayer().setLocation(37);
        }
    }
}
