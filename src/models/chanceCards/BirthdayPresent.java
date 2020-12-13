package models.chanceCards;

import models.Card;
import models.Game;

public class BirthdayPresent implements Card {
    // take $1000 from each player fro your birthday
    @Override
    public void executeAction(Game game) {
        int numberOfPlayers = game.getPlayerNumber();
        int loopCount = 0;
        while(loopCount < numberOfPlayers)
        {
            if(game.getPlayer(loopCount) == game.getCurrentPlayer())
            {
                loopCount++;
                continue;
            }
            game.getPlayer(loopCount).removeMoney(1000);
            loopCount++;
        }
        game.getCurrentPlayer().addMoney( 1000 * (numberOfPlayers - 1) );
    }
}
