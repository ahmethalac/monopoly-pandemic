package models;

public class City extends Region {
    private Buildings buildings;
    private Player owner;
    private double price;
    private boolean isMortgaged;
    private boolean isInfected;
    private String name;
    private final double MORGAGE_PENALTY = 0.8;
    private final double MORGAGE_RATE = 0.7;

    public City(double price, double[] rents, String name, int id) {
        super(id);
        buildings = new Buildings(rents);
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
        isInfected = bool;
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

    public int getBuildingPrice(int count) {
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
}
