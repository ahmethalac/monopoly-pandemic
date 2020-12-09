package storage.models;

public class City extends Region {
    private Buildings buildings;
    private Player owner;
    private double [] rents;
    private double price;
    private boolean isMortgaged;
    private boolean isInfected;

    public City(double price, double[]rents, String name, int id){
        super(name, id);
        buildings = new Buildings();
        owner = null;
        this.rents = rents;
        this.price = price;
        isMortgaged = false;
        isInfected = false;
    }

    public void mortgage(boolean bool){
        isMortgaged = bool;
    }

    public void setOwner(Player player){
        owner = player;
    }

    public void addBuilding(int count){ buildings.addBuilding(count); }

    public boolean removeBuilding(int count){
        return buildings.removeBuilding(count);
    }

    public void infect(boolean bool){
        isInfected = bool;
    }

    public boolean isInfected(){
        return isInfected;
    }

    public boolean isMortgaged(){
        return isMortgaged;
    }

    public double getPrice(){
        return price;
    }
    public int getNumberOfBuildings(){
        return buildings.getNumberOfBuildings();
    }
    public int getBuildingPrice(int count) { return buildings.getBuildingPrice(count); }
    public double getRent(){
        return buildings.getRent();
    }
    public Player getOwner() { return owner; }
}
