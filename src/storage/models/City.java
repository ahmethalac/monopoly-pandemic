package storage.models;

public class City extends Region {
    private int numberOfBuildings;
    private Player owner;
    private double [] rents;
    private double price;
    private boolean isMortgaged;
    private boolean isInfected;

    public City(double price, double[]rents, String name, int id){
        super(name, id);
        numberOfBuildings = 0;
        owner = null;
        this.rents = rents;
        this.price = price;
        isMortgaged = false;
        isInfected = false;
    }

    public void mortgage(boolean bool){
        if(bool){
            isMortgaged = true;
        }

    }

    public void setOwner(Player player){
        owner = player;
    }

    public void addBuilding(int numberOfBuildings){ this.numberOfBuildings += numberOfBuildings; }

    public void removeBuilding(int numberOfBuildings){
        this.numberOfBuildings -= numberOfBuildings;
    }

    public void infect(boolean bool){
        if(bool) {
            isInfected = true;
        }
    }

    public boolean isInfected(){
        if(isInfected){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isMortgaged(){
        if(isMortgaged){
            return true;
        }
        else{
            return false;
        }
    }

    public double getPrice(){
        return price;
    }
    public int getNumberOfBuildings(){
        return numberOfBuildings;
    }
    public double getRent(){
        return rents[numberOfBuildings];
    }
}
