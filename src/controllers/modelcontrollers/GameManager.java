package controllers.modelcontrollers;

import controllers.observers.GameLogObserver;
import controllers.observers.PlayerObserver;
import controllers.scenecontrollers.GameSceneController;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import models.*;
import storage.filemanager.DataManager;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;

public class GameManager {

    // properties
    private static GameManager instance = null;
    private Game game;
    private int turnCounter = 0;
    private int tourCounter = 0;
    private static int NUMBER_OF_REGIONS = 40;
    private boolean diceRolled = false;
    private Card currentChanceCard = null;


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
        ArrayList<String> chanceCardNames = DataManager.getInstance().getChanceCardNames();
        game = new Game(players,regions,chanceCardNames);
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
        if(!getCurrentPlayer().isInQuarantine()){
            if(this.game.getCurrentPlayer().getLocation() + count >= (NUMBER_OF_REGIONS - 1)
                    && this.game.getCurrentPlayer().isInfected()){
                this.game.getCurrentPlayer().setLocation(NUMBER_OF_REGIONS - 1);
                TestRegion tr = (TestRegion) this.game.getRegion(39);
                tr.performRegionAction();
            }
            else {
                int newLocation;
                // add money if player pass over starting point
                if (this.game.getCurrentPlayer().getLocation() + count > NUMBER_OF_REGIONS - 1) {
                    StartingRegion sr = (StartingRegion) this.game.getRegion(0);
                    sr.performRegionAction();
                    newLocation = count % NUMBER_OF_REGIONS;
                } else {
                    newLocation = this.game.getCurrentPlayer().getLocation() + count;
                }
                this.game.getCurrentPlayer().setLocation(newLocation);
            }
        }
    }

    // picks chance card from top and performs operation on currentPlayer
    public void pickChanceCard(){
        currentChanceCard = this.game.getCurrentChanceCard();
        currentChanceCard.executeAction(this.game);
    }

    // picks chance card from top and performs operation on currentPlayer
    public Card getCurrentChanceCard(){
        return currentChanceCard;
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
            currentPlayer.addCity(currentCity);
            currentCity.setOwner(currentPlayer);
            possible = true;
        }
        return possible;
    }

    public boolean buyBuilding(City city, int count, Player currentPlayer){
        boolean leftIsOwned;
        boolean leftMostIsOwned;
        boolean rightIsOwned;
        boolean rightMostIsOwned;
        boolean cityIsOwned;
        leftIsOwned = isCityOwned(city, currentPlayer, -1);
        leftMostIsOwned = isCityOwned(city, currentPlayer, -2);
        rightIsOwned = isCityOwned(city, currentPlayer, 1);
        rightMostIsOwned = isCityOwned(city, currentPlayer, 2);
        cityIsOwned = (city.getOwner().getId() == currentPlayer.getId());

        if((leftMostIsOwned && leftIsOwned && cityIsOwned)
                || (leftIsOwned && cityIsOwned && rightIsOwned)
                || (cityIsOwned && rightIsOwned && rightMostIsOwned) ) {
            if(currentPlayer.getMoney() >= city.getBuildingPrice(count)){
                currentPlayer.removeMoney(city.getBuildingPrice(count));
                city.addBuilding(count);
                return true;
            }
        }
        return false;
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
        game.getCurrentPlayer().notifyAllObservers();
    }

    public void runPerformRegionAction(){
        Platform.runLater(GameManager.getInstance()::performRegionAction);
    }

    private boolean performRegionAction(){
        boolean performed = false;
        Player currentPlayer = this.game.getCurrentPlayer();
        Region currentRegion = (this.game.getRegion(currentPlayer.getLocation()));
        if(currentRegion instanceof City) {
            performed = checkAgreements();
            if(!performed){
                GameSceneController.handleBuyCityPopup();
            }
        }
        else if(currentRegion instanceof ChanceRegion) {
            ((ChanceRegion) currentRegion).performRegionAction();
            GameSceneController.handleChanceRegionPopup();
            performed = true;
        }
        else if(currentRegion instanceof PirateRegion) {
            ((PirateRegion) currentRegion).performRegionAction();
            GameSceneController.handlePirateRegionPopup();
            performed = true;
        }
        else if(currentRegion instanceof StartingRegion) {
            performed = true;
        }
        else if(currentRegion instanceof QuarantineRegion) {
            ((QuarantineRegion) currentRegion).performRegionAction();
            performed = true;
        }
        checkPandemic();
        return performed;
    }

    // dice[2] contains total dice numbers
    public int[] rollDice(){
        if(!diceRolled){
            diceRolled = true;
            int[] dice = new int[3];
            dice[0] = (int) (6*Math.random()) + 1;
            dice[1] = (int) (6*Math.random()) + 1;
            dice[2] = dice[0] + dice[1];
            return dice;
        }
        else{
            return null;
        }
    }

    // operations to perform when a turn passes
    public void endTurn(){
        diceRolled = false;
        this.game.nextPlayer();

        // check update functions
        this.offerAgreement();
        this.game.checkVirus();

        if(tourCounter == 0) {
            // TODO remove infected cities infection
            infectRandomCity();
        }
        turnCounter++;
        turnCounter = turnCounter % this.game.getPlayerNumber();
        if(turnCounter == 0) {
            tourCounter++;
        }
    }

    public void setPlayerObservers(GameSceneController controller, VBox gameLog){
        for ( Player player : this.game.getPlayers()){
            new PlayerObserver(player, controller);
            new GameLogObserver(player, gameLog);
        }
        this.game.getCurrentPlayer().notifyAllObservers();
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

    public boolean isDiceRolled(){
        return diceRolled;
    }

    public int getTour(){
        return tourCounter;
    }

    public Game getGame() { return this.game; }

    // Private methods

    private boolean checkPandemic(){
        for(int i = 0; i<this.game.getPlayerNumber(); i++){
            if(!this.game.getPlayer(i).isInfected()){
                return false;
            }
        }
        return true;
    }

    // returns true if operation performed for currentCity
    private boolean checkAgreements(){
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
                    try {
                        currentPlayer.removeMoney(currentCity.getRent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    currentCity.getOwner().addMoney(currentCity.getRent());
                }
            }
            return true;
        }
        return false;
    }

    // will return true if city located at the (city + leftOrRight) is owned by the player
    private boolean isCityOwned(City city, Player currentPlayer, int leftOrRight){
        int regionID = city.getId() + leftOrRight < 0 ?
                city.getId() + leftOrRight + NUMBER_OF_REGIONS :
                city.getId() + leftOrRight % NUMBER_OF_REGIONS;
        if(this.game.getRegion(regionID ) instanceof City){
            if (((City) this.game.getRegion(regionID )).getOwner() != null){
                if(((City) this.game.getRegion(regionID )).getOwner().getId()
                        == currentPlayer.getId()){
                    return true;
                }
            }
        }
        return false;
    }

    // will return if agreement need to be offered
    private void offerAgreement() {
        Agreement agreement = game.offerAgreement();
        if(agreement != null){
            GameSceneController.handleAgreementOfferPopup(agreement);
        }
    }
}
