package storage.models;

import java.util.ArrayList;

public class Game {

    // properties
    private static Game game;
    private ArrayList<Player> players;
    private ArrayList<Region> regions;
    private ArrayList<Card> chanceCards;
    private ArrayList<Agreement> agreements;
    private int currentPlayer;

    // constructor
    private Game(ArrayList<Player> players){
        players = new ArrayList<>(players);
        regions = new ArrayList<>();
        chanceCards = new ArrayList<>();
        agreements = new ArrayList<>();
        currentPlayer = ((int)(Math.random()*10))%(players.size());
    }

    // methods
    public static Game getInstance(ArrayList<Player> players) {
        if(game == null) {
            game = new Game(players);
        }
        return game;
    }

    public static Game getInstance(){
        return game;
    }

    public static void setInstance(Game saveGame ){
        game = saveGame;
    }

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public Region getRegion(int id) {
        return regions.get(id);
    }

    public int getRegionNumber() {
        return regions.size();
    }

    public void addAgreement(Agreement agreement) {
        agreements.add(agreement);
    }

    public boolean removeAgreement(Agreement agreement) {
        return agreements.remove(agreement);
    }

    public Player getCurrentPlayer() {
        for ( Player player : players ) {
            if(player.getId() == currentPlayer){
                return player;
            }
        }
        return null;
    }

    public Card getCurrentChanceCard() {
        Card currentChanceCard = chanceCards.get(0);
        chanceCards.remove(0);
        chanceCards.add(currentChanceCard);
        return currentChanceCard;
    }

    public void nextPlayer() {
        currentPlayer++;
        if(currentPlayer > players.size()){
            currentPlayer = 1;
        }
    }
}
