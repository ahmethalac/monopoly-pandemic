package controllers.modelcontrollers;

import storage.models.*;

import java.util.ArrayList;

public class GameManager {

    // properties
    private ArrayList<Player> players;
    private int turnCounter = 0;
    private int tourCounter = 0;

    // constructors
    public GameManager(ArrayList<Player> players){
        this.players = players;
    }

    public GameManager(Game saveGame){
        Game.setInstance(saveGame);
    }

    // methods

    // infects a random city in a game
    public void infectRandomCity() {
        int random = (int) (Game.getInstance().getRegionNumber() * Math.random());
        while( !(Game.getInstance().getRegion(random) instanceof City) ){
            random = (int) (Game.getInstance().getRegionNumber() * Math.random());
        }
        ((City) Game.getInstance().getRegion(random)).infect(true);
    }

    // moves current player count number of steps
    public void moveForward(int count) {
        Game.getInstance().getCurrentPlayer().setLocation(Game.getInstance().getCurrentPlayer().getLocation() + count);
    }

    // picks chance card from top and performs operation on currentPlayer
    public void pickChanceCard(){
        Game.getInstance().getCurrentChanceCard().executeAction(Game.getInstance());
    }

    // set a new agreement
    public void newAgreement(Offer firstOffer, Offer secondOffer,
                             Player firstPlayer, Player secondPlayer, String agreementName){
        Game.getInstance().addAgreement(new Agreement(firstOffer, secondOffer, firstPlayer, secondPlayer, agreementName));
    }

    // delete an existing agreement
    public boolean deleteAgreement(String agreementName){
        return Game.getInstance().removeAgreement(agreementName);
    }

    // return Agreement names for render
    public ArrayList<Agreement> getAgreementNames(){
        return Game.getInstance().getAgreements();
    }

    // buy current location if possible
    public boolean buyCurrentLocation(){
        boolean possible = false;
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        City currentCity = (City) (Game.getInstance().getRegion(currentPlayer.getLocation()));
        if(currentPlayer.getMoney() >= currentCity.getPrice()){
            currentPlayer.removeMoney(currentCity.getPrice());
            currentPlayer.addCity((City) Game.getInstance().getRegion(currentPlayer.getLocation()));
            possible = true;
        }
        return possible;
    }

    public boolean buyBuilding(City city, int count){
        boolean possible = false;
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        if(currentPlayer.getMoney() >= city.getBuildingPrice(count)){
            currentPlayer.removeMoney(city.getBuildingPrice(count));
            city.addBuilding(count);
            return true;
        }
        return true;
    }

    public boolean sellBuilding(City city, int count){
        boolean possible;
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        possible = city.removeBuilding(count);
        if(possible){
            currentPlayer.addMoney(city.getBuildingPrice(count));
        }
        return possible;
    }

    public void mortgageCity(City city, boolean bool) {
        city.mortgage(bool);
    }

    public void checkAgreements(){
        ArrayList<Agreement> agreements = Game.getInstance().getAgreements();
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        City currentCity = (City) (Game.getInstance().getRegion(currentPlayer.getLocation()));
        boolean offerPerformed = false;

        if(currentCity.getOwner() != null){
            if(currentCity.getOwner() != currentPlayer){
                for( Agreement agreement : agreements ) {
                    if(agreement.checkOffers(currentCity)) {
                        offerPerformed = true;
                        agreement.performOffers(currentCity, currentPlayer);
                    }
                }
                if(!offerPerformed) {
                    currentPlayer.removeMoney(currentCity.getRent());
                    currentCity.getOwner().addMoney(currentCity.getRent());
                }
            }
        }
    }

    public boolean checkPandemic(){
        for(int i = 0; i<Game.getInstance().getPlayerNumber(); i++){
            if(!Game.getInstance().getPlayer(i).isInfected()){
                return false;
            }
        }
        return true;
    }

    public void performRegionAction(){
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        Region currentRegion = (Game.getInstance().getRegion(currentPlayer.getLocation()));
        if(currentRegion instanceof ChanceRegion) {
            pickChanceCard();
        }
        else if(currentRegion instanceof SpecialRegion) {
            // TODO play mini game
        }
        else if(currentRegion instanceof City){
            checkAgreements();
        }
    }

    // dice[2] contains total dice numbers
    public int[] rollDice(){
        int[] dice = new int[3];
        dice[0] = (int) (6*Math.random());
        dice[1] = (int) (6*Math.random());
        dice[2] = dice[0] + dice[1];
        return dice;
    }

    // operations to perform when a turn passes
    public void nextTurn(){
        if(tourCounter == 0) {
            // TODO remove infected cities infection
            infectRandomCity();
        }
        turnCounter++;
        turnCounter = turnCounter % players.size();
        if(turnCounter == 0){
            tourCounter++;
        }
    }

}