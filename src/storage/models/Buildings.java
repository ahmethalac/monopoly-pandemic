package storage.models;

public class Buildings {

    // properties
    private int numberOfBuildings;
    private final int MAX_NUMBER_OF_BUILDINGS = 5;
    private int[] prices;
    private int[] rents;

    // constructor
    public Buildings() {
        numberOfBuildings = 0;
        prices = new int[MAX_NUMBER_OF_BUILDINGS];
        rents = new int[MAX_NUMBER_OF_BUILDINGS];
    }

    // methods

    // add a building if not at max
    public boolean addBuilding(int count) {
        if(numberOfBuildings + count <= MAX_NUMBER_OF_BUILDINGS){
            numberOfBuildings += count;
            return true;
        }
        return false;
    }

    public boolean removeBuilding(int count) {
        if(numberOfBuildings - count >= 0) {
            numberOfBuildings -= count;
            return true;
        }
        return false;
    }

    public int getNumberOfBuildings(){
        return numberOfBuildings;
    }

    // return prices for count number of buildings.
    public int getBuildingPrice(int count){
        if(numberOfBuildings + count <= MAX_NUMBER_OF_BUILDINGS){
            int price = 0;
            for(int i = numberOfBuildings; i< numberOfBuildings + count; i++){
                price += prices[i-1];
            }
            return price;
        }
        return -1;
    }

    public int getRent(){
        return rents[numberOfBuildings-1];
    }

}
