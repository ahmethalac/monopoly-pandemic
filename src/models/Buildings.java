package models;

public class Buildings {

    // properties
    private int numberOfBuildings;
    private final int MAX_NUMBER_OF_BUILDINGS = 5;
    private final double RENT_OVER_PRICE_RATE = 0.5;
    private double price;
    private double[] rents;

    // constructor
    public Buildings(double[] rents, double cityPrice) {
        numberOfBuildings = 0;
        this.rents = rents;
        price = cityPrice * RENT_OVER_PRICE_RATE;
    }

    // methods

    // add a building if not at max
    public boolean addBuilding(int count) {
        if (numberOfBuildings + count <= MAX_NUMBER_OF_BUILDINGS) {
            numberOfBuildings += count;
            return true;
        }
        return false;
    }

    public boolean removeBuilding(int count) {
        if (numberOfBuildings - count >= 0) {
            numberOfBuildings -= count;
            return true;
        }
        return false;
    }

    public int getNumberOfBuildings() {
        return numberOfBuildings;
    }

    // return prices for count number of buildings.
    public double getBuildingPrice(int count) {
        if (numberOfBuildings + count <= MAX_NUMBER_OF_BUILDINGS) {
            double price = this.price*count;
            return price;
        }
        return -1;
    }

    public double getRent() {
        return rents[numberOfBuildings];
    }

    public double[] getRents() {
        return rents;
    }
}
