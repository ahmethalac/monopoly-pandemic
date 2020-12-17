package models;
import controllers.modelcontrollers.GameManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Observable implements Serializable {
    private boolean isInQuarantine;
    private ArrayList<City> cities;
    private String name;
    private int id;
    private String color;
    private String pawn;
    private double money;
    private boolean isBankrupted;
    private int location;
    private boolean isInfected;
    private int quarantineTourCounter = -1;

    public Player(String name, String color, String pawn, int id)
    {
        isInQuarantine = false;
        cities = new ArrayList<>();
        this.name = name;
        this.id = id;
        this.color = color;
        this.pawn = pawn;
        money = 200000;
        isBankrupted = false;
        location = 0;
        isInfected = false;
    }

    public void bankrupt() { isBankrupted = true; }

    public void quarantine(boolean bool)
    {
        if(bool){
            // TODO SET LOCATION TO QUARANTINE REGION ID this.location();
            if(!isInQuarantine){
                quarantineTourCounter = GameManager.getInstance().getTour();
            }
        }
        isInQuarantine = bool;
    }

    public boolean checkQuarantine(){
        if(GameManager.getInstance().getTour() - quarantineTourCounter >= 3){
            isInQuarantine = false;
            quarantineTourCounter = -1;
            return true;
        }
        return false;
    }

    public void addMoney(double money)
    {
        this.money = this.money + money;
        if(isBankrupted && money > 0){
            isBankrupted = false;
        }
        this.notifyAllObservers();
    }

    public void removeMoney(double money)
    {
        this.money = this.money - money;
        if(this.money < 0){
            isBankrupted = true;
        }
        this.notifyAllObservers();
    }

    public void infect(boolean bool)
    {
        isInfected = bool;
    }

    public boolean removeCity(City city){
        if (cities.remove(city)){
            this.notifyAllObservers();
            return true;
        }
        return false;
    }

    public void addCity(City city){
        cities.add(city);
        this.notifyAllObservers();
    }

    //  Getters - Setters

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setLocation(int regionID)
    {
        location = regionID;
        this.notifyAllObservers();
    }

    public int getLocation()
    {
        return location;
    }

    public void setId(int id) { this.id = id; }

    public int getId()
    {
        return id;
    }

    public void setColor(String color) { this.color = color; }

    public String getColor()
    {
        return color;
    }

    public void setPawn(String pawn) { this.pawn = pawn; }

    public String getPawn() { return pawn; }

    public double getMoney()
    {
        return money;
    }

    public boolean isBankrupted()
    {
        return isBankrupted;
    }

    public boolean isInfected()
    {
        return isInfected;
    }

    public void setName(String name) { this.name = name; }

    public String getName()
    {
        return name;
    }

    public boolean isInQuarantine()
    {
        return isInQuarantine;
    }

    @Override
    public String toString() {
        return "Player{" +
                "  name='" + name + '\'' +
                ", id=" + id +
                ", color='" + color + '\'' +
                ", pawn='" + pawn + '\'' +
                ", money=" + money +
                ", isBankrupted=" + isBankrupted +
                ", location=" + location +
                ", isInfected=" + isInfected +
                "}\n";
    }
}
