package controllers.modelcontrollers;

import controllers.observers.PlayerObserver;
import controllers.scenecontrollers.GameSceneController;
import models.*;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    // properties
    private static GameManager instance = null;
    private Game game;
    private int turnCounter = 0;
    private int tourCounter = 0;
    private final int PIRATE_FEE = 10000;
    private final int STARTING_REGION_BONUS = 20000;

    public static GameManager getInstance(){
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void setGame(Game saveGame){
        game = new Game(saveGame);
    }
    // methods

    // setup a game
    public void initGame(ArrayList<Player> players) {
        ArrayList<Region> regions = SettingImporter.getRegions();
        game = new Game(players,regions);
    }

    public ArrayList<Region> getRegions(){
        return this.game.getRegions();
    }

    // infects a random city in a game
    public void infectRandomCity() {
        int random = (int) (this.game.getRegionNumber() * Math.random());
        while( !(this.game.getRegion(random) instanceof City) ){
            random = (int) (this.game.getRegionNumber() * Math.random());
        }
        ((City) this.game.getRegion(random)).infect(true);
    }

    // moves current player count number of steps
    public void moveForward(int count) {
        this.game.getCurrentPlayer().setLocation(this.game.getCurrentPlayer().getLocation() + count);
    }

    // picks chance card from top and performs operation on currentPlayer
    public void pickChanceCard(){
        this.game.getCurrentChanceCard().executeAction(this.game);
    }

    // set a new agreement
    public void newAgreement(Offer firstOffer, Offer secondOffer,
                             Player firstPlayer, Player secondPlayer, String agreementName){
        this.game.addAgreement(new Agreement(firstOffer, secondOffer, firstPlayer, secondPlayer, agreementName));
    }

    // delete an existing agreement
    public boolean deleteAgreement(String agreementName){
        return this.game.removeAgreement(agreementName);
    }

    // return Agreement names for render
    public ArrayList<Agreement> getAgreementNames(){
        return this.game.getAgreements();
    }

    // buy current location if possible
    public boolean buyCurrentLocation(){
        boolean possible = false;
        Player currentPlayer = this.game.getCurrentPlayer();
        City currentCity = (City) (this.game.getRegion(currentPlayer.getLocation()));
        if(currentPlayer.getMoney() >= currentCity.getPrice()){
            currentPlayer.removeMoney(currentCity.getPrice());
            currentPlayer.addCity((City) this.game.getRegion(currentPlayer.getLocation()));
            possible = true;
        }
        return possible;
    }

    public boolean buyBuilding(City city, int count, Player currentPlayer){
        boolean possible = false;
        if(currentPlayer.getMoney() >= city.getBuildingPrice(count)){
            currentPlayer.removeMoney(city.getBuildingPrice(count));
            city.addBuilding(count);
            return true;
        }
        return true;
    }

    public boolean sellBuilding(City city, int count, Player currentPlayer){
        boolean possible;
        possible = city.removeBuilding(count);
        if(possible){
            currentPlayer.addMoney(city.getBuildingPrice(count));
        }
        return possible;
    }

    public void mortgageCity(City city, boolean bool) {
        city.mortgage(bool);
    }

    private void checkAgreements(){
        ArrayList<Agreement> agreements = this.game.getAgreements();
        Player currentPlayer = this.game.getCurrentPlayer();
        City currentCity = (City) (this.game.getRegion(currentPlayer.getLocation()));
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

    private boolean checkPandemic(){
        for(int i = 0; i<this.game.getPlayerNumber(); i++){
            if(!this.game.getPlayer(i).isInfected()){
                return false;
            }
        }
        return true;
    }

    public void performRegionAction(){
        Player currentPlayer = this.game.getCurrentPlayer();
        Region currentRegion = (this.game.getRegion(currentPlayer.getLocation()));
        if(currentRegion instanceof City) {
            checkAgreements();
        }
        else if(currentRegion instanceof ChanceRegion) {
            pickChanceCard();
        }
        else if(currentRegion instanceof PirateRegion) {
            pirateAction(currentPlayer);
        }

        // add money if player pass over starting point
        if(currentPlayer.getLocation() <= currentRegion.getId()){
            startingRegionAction(currentPlayer);
        }
        currentPlayer.setLocation(currentRegion.getId());
        checkPandemic();
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
    public void endTurn(){
        if(tourCounter == 0) {
            // TODO remove infected cities infection
            infectRandomCity();
        }
        turnCounter++;
        turnCounter = turnCounter % this.game.getPlayerNumber();
        if(turnCounter == 0){
            tourCounter++;
        }
    }

    public void setPlayerObservers(GameSceneController controller){
        for ( Player player : this.game.getPlayers()){
            new PlayerObserver(player, controller);
        }
        this.game.getCurrentPlayer().notifyAllObservers();
    }

    private void pirateAction(Player currentPlayer){
        currentPlayer.removeMoney(PIRATE_FEE);
    }

    private void startingRegionAction(Player currentPlayer){
        currentPlayer.addMoney(STARTING_REGION_BONUS);
    }

    public Player getCurrentPlayer(){
        return this.game.getCurrentPlayer();
    }

    public ArrayList<Player> getPlayers(){
        return this.game.getPlayers();
    }

    public Region getCurrentRegion(){
        return this.game.getRegion(this.game.getCurrentPlayer().getLocation());
    }
}
