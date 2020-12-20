package models;

import controllers.modelcontrollers.GameManager;

public class City extends Region {
    private Buildings buildings;
    private Player owner;
    private double price;
    private boolean isMortgaged;
    private boolean isInfected;
    private String name;
    private final double MORGAGE_PENALTY = 0.55;
    private final double MORGAGE_RATE = 0.5;
    private int infectTourCounter;
    private final int INFECTION_TIME = 2;

    public City(double price, double[] rents, String name, int id) {
        super(id);
        buildings = new Buildings(rents, price);
        owner = null;
        this.price = price;
        isMortgaged = false;
        isInfected = false;
        this.name = name;
    }

    public void mortgage(boolean bool) {
        if (bool && !isMortgaged) {
            owner.addMoney(price * MORGAGE_RATE);
            isMortgaged = bool;
        } else if (!bool && isMortgaged) {
            owner.removeMoney(price * MORGAGE_PENALTY);
            isMortgaged = bool;
        }
    }

    public void setOwner(Player player) {
        owner = player;
        this.notifyAllObservers();
    }

    public void addBuilding(int count) {
        buildings.addBuilding(count);
        this.notifyAllObservers();
    }

    public boolean removeBuilding(int count) {
        if ( buildings.removeBuilding(count)){
            this.notifyAllObservers();
            return true;
        }
        return false;
    }

    public void infect(boolean bool) {
        if(bool){
            if(!isInfected){
                infectTourCounter = GameManager.getInstance().getTour();
            }
        }
        isInfected = bool;
        this.notifyAllObservers();
    }

    public boolean checkInfection(){
        if(isInfected){
            int tourToDisinfect = infectTourCounter + INFECTION_TIME;
            if( tourToDisinfect == GameManager.getInstance().getTour()){
                infect(false);
                return true;
            }
        }
        return false;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfBuildings() {
        return buildings.getNumberOfBuildings();
    }

    public double getBuildingPrice(int count) {
        return buildings.getBuildingPrice(count);
    }

    public double getRent() {
        return buildings.getRent();
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Buildings getBuildings() {
        return buildings;
    }

    @Override
    public String toString() {
        return name;
    }
}
