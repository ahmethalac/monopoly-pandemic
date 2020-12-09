package controllers.modelcontrollers;

import storage.models.City;
import storage.models.Game;
import storage.models.Player;

import java.util.ArrayList;

public class GameManager {

    // properties
    private ArrayList<Player> players;

    // constructors
    public GameManager(ArrayList<Player> players){
        this.players = players;
    }

    public GameManager(Game saveGame){
        Game.setInstance(saveGame);
    }

    // methods
    public void infectRandomCity() {
        int random = (int) (Game.getInstance().getRegionNumber() * Math.random());
        while( !(Game.getInstance().getRegion(random) instanceof City) ){
            random = (int) (Game.getInstance().getRegionNumber() * Math.random());
        }
        ((City) Game.getInstance().getRegion(random)).infect(true);
    }

    public void moveForward(int count) {
        Game.getInstance().getCurrentPlayer().setLocation(Game.getInstance().getCurrentPlayer().getLocation() + count);
    }

    public void pickChanceCard(){
        // TODO use cardActionPerformer
        // game.getCurrentChangeCard().performAction(game.getCurrentPlayer());
    }

}
