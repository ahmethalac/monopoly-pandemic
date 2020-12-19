package models.chanceCards;

import models.Card;
import models.Game;

public class RepairEveryBuilding implements Card {
    @Override
    public String getCardString() {
        return "Repair every building, $1000 for each house, $5000 for each hotel";
    }

    @Override
    public void executeAction(Game game) {
        int numOfPlayers = game.getPlayers().size();
        int totalNumOfBuildings = 0;
        for(int i = 0; i < numOfPlayers; i++)
        {
            int numOfCities = game.getPlayers().get(i).getCities().size();
            for(int j = 0; j < numOfCities; j++)
            {
                totalNumOfBuildings = game.getPlayers().get(i).getCities().get(j).getNumberOfBuildings();
            }
        }
        game.getCurrentPlayer().removeMoney(totalNumOfBuildings * 1000);
    }
}
