package models;

public class Buildings {

    // properties
    private int numberOfBuildings;
    private final int MAX_NUMBER_OF_BUILDINGS = 5;
    private double[] prices;
    private double[] rents;

    // constructor
    public Buildings(double[] rents) {
        numberOfBuildings = 0;
        this.rents = rents;
        prices = new double[MAX_NUMBER_OF_BUILDINGS];
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
    public int getBuildingPrice(int count) {
        if (numberOfBuildings + count <= MAX_NUMBER_OF_BUILDINGS) {
            int price = 0;
            for (int i = numberOfBuildings; i < numberOfBuildings + count; i++) {
                price += prices[i];
            }
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
