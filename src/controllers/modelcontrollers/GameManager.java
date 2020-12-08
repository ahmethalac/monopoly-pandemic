package controllers.modelcontrollers;

import storage.models.City;
import storage.models.Game;
import storage.models.Player;

import java.util.ArrayList;

public class GameManager {

    // properties
    private Game game;
    private ArrayList<Player> players;

    // constructors
    public GameManager(ArrayList<Player> players){
        this.players = players;
        game = Game.getInstance(players);
    }

    public GameManager(Game saveGame){
        Game.setInstance(saveGame);
        game = Game.getInstance();
    }

    // methods
    public void infectRandomCity() {
        int random = (int) (game.getRegionNumber() * Math.random());
        while( !(game.getRegion(random) instanceof City) ){
            random = (int) (game.getRegionNumber() * Math.random());
        }
        ((City) game.getRegion(random)).infect(true);
    }

    public void moveForward(int count) {
        game.getCurrentPlayer().setLocation(game.getCurrentPlayer().getLocation() + count);
    }

    public void pickChanceCard(){
        // TODO use cardActionPerformer
        // game.getCurrentChangeCard().performAction(game.getCurrentPlayer());
    }

}
