package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Game implements Serializable {

    // properties
    private ArrayList<Player> players;
    private ArrayList<Region> regions;
    private ArrayList<Card> chanceCards;
    private ArrayList<Agreement> agreements;
    private int currentPlayer;

    // constructor
    public Game(ArrayList<Player> players, ArrayList<Region> regions, ArrayList<String> chanceCardNames){
        agreements = new ArrayList<>();
        currentPlayer = 0;
        this.players = players;
        this.regions = regions;
        this.chanceCards = new ArrayList<>();
        try {
            for(String name: chanceCardNames) {
                Class chanceCard = Class.forName("models.chanceCards." + name);
                Object o = chanceCard.getDeclaredConstructor().newInstance();
                chanceCards.add((Card) o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.shuffle(chanceCards);
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
        return currentChanceCard;
    }

    public void nextPlayer() {
        currentPlayer++;
        if(currentPlayer == players.size()){
            currentPlayer = 0;
        }
        players.get(currentPlayer).notifyAllObservers();
    }

    public void quarantinePlayer() {
        players.get(currentPlayer).quarantine(true);
    }

    public void checkVirus() {
        for( Player player : players ){
            if(player.isInQuarantine()){
                player.checkQuarantine();
                player.checkInfection();
            }
        }
        for( Region region : regions){
            if(region instanceof City){
                ((City) region).checkInfection();
            }
        }
    }

    public ArrayList<Agreement> getAgreements(){
        return agreements;
    }

    public int getPlayerNumber(){
        return players.size();
    }

    public Agreement offerAgreement(){
        for(Agreement agreement: agreements){
            if(!agreement.isOffered() && (agreement.getSecondPlayer().getId() == currentPlayer)){
                return agreement;
            }
        }
        return null;
    }

}
