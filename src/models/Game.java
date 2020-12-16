package models;

import java.util.ArrayList;

public class Game {

    // properties
    private ArrayList<Player> players;
    private ArrayList<Region> regions;
    private ArrayList<Card> chanceCards;
    private ArrayList<Agreement> agreements;
    private int currentPlayer;

    // constructor
    public Game(ArrayList<Player> players, ArrayList<Region> regions){
        agreements = new ArrayList<>();
        currentPlayer = 0;
        this.players = players;
        this.regions = regions;
    }

    // copy constructor to load game
    public Game(Game game){
        this.players = game.players;
        this.regions = game.regions;
        this.chanceCards = game.chanceCards;
        this.agreements = game.agreements;
        this.currentPlayer = game.currentPlayer;
    }

    // methods
    public Player getPlayer(int id) {
        return players.get(id);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Region getRegion(int id) {
        return regions.get(id);
    }

    public ArrayList<Region> getRegions() {
        return regions;
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
        return players.isEmpty() ? null : players.get(currentPlayer);
    }

    public Card getCurrentChanceCard() {
        Card currentChanceCard = chanceCards.get(0);
        chanceCards.remove(0);
        chanceCards.add(currentChanceCard);
        // TODO OBSERVE
        return currentChanceCard;
    }

    public void nextPlayer() {
        currentPlayer++;
        if(currentPlayer == players.size()){
            currentPlayer = 0;
        }
        players.get(currentPlayer).notifyAllObservers();
    }

    public ArrayList<Agreement> getAgreements(){
        return agreements;
    }

    public int getPlayerNumber(){
        return players.size();
    }

}
