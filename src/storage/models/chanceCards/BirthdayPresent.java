package storage.models.chanceCards;

import storage.models.Card;
import storage.models.Game;

public class BirthdayPresent implements Card {
    // take $1000 from each player fro your birthday
    @Override
    public void executeAction() {
        int numberOfPlayers = Game.getInstance().getPlayerArraySize();
        int loopCount = 0;
        while(loopCount < numberOfPlayers)
        {
            if(Game.getInstance().getPlayer(loopCount) == Game.getInstance().getCurrentPlayer())
            {
                loopCount++;
                continue;
            }
            Game.getInstance().getPlayer(loopCount).removeMoney(1000);
            loopCount++;
        }
        Game.getInstance().getCurrentPlayer().addMoney( 1000 * (numberOfPlayers - 1) );
    }
}
