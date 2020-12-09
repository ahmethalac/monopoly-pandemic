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
    private Game(){
        regions = new ArrayList<>();
        // TODO set regions
        chanceCards = new ArrayList<>();
        // TODO set chanceCards
        agreements = new ArrayList<>();
    }

    // methods
    public static Game getInstance() {
        if(game == null) {
            game = new Game();
        }
        return game;
    }

    public static void setInstance(Game saveGame ){
        game = saveGame;
    }

    public boolean setPlayers(ArrayList<Player> players) {
        if(this.players == null){
            this.players = players;
            currentPlayer = players.isEmpty() ? 0 : ((int)(Math.random()*10))%(players.size());
            return true;
        }
        return false;
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
        if(agreement.isEmpty()){
            agreements.remove(agreement);
        }
    }

    public boolean removeAgreement(String agreementName) {
        for (Agreement agreement : agreements){
            if(agreement.getName().equals(agreementName)){
                 return agreements.remove(agreement);
            }
        }
        return false;
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

    public String[] getAgreementNames(){
        String[] agreementNames = new String[agreements.size()];
        int i = 0;
        for(Agreement agreement : agreements){
            agreementNames[i] = agreement.getName();
            i++;
        }
        return agreementNames;
    }
}
