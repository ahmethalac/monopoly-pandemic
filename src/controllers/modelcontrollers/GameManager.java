package controllers.modelcontrollers;

import storage.models.*;

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
        Game.getInstance().getCurrentChanceCard().executeAction(Game.getInstance().getCurrentPlayer());
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
    public String[] getAgreementNames(){
        return Game.getInstance().getAgreementNames();
    }

    // buy current location if possible
    public boolean buyCurrentLocation(){
        return Game.getInstance().getCurrentPlayer().buyCity
                ((City) Game.getInstance().getRegion(Game.getInstance().getCurrentPlayer().getLocation()));
    }

    //public boolean buyBuilding(City city, int count){

    //}



}
