package storage.models;

public class Player {
    private boolean isInQuarantine;
    // TODO - private City[] cities;
    private String name;
    private int id;
    private String color;
    private String pawn;
    private double money;
    private boolean isBankrupted;
    private int location;
    private boolean isInfected;

    public Player()
    {
        isInQuarantine = false;
        // TODO - add cities
        name = "";
        id = 0;
        color = "";
        pawn = "";
        money = 0;
        isBankrupted = false;
        location = 0;
        isInfected = false;
    }
    public void bankrupt()
    {
        isBankrupted = true;
    }

    public void quarantine(boolean bool)
    {
        isInQuarantine = bool;
    }

    public void setLocation(int regionID)
    {
        location = regionID;
    }

    public void addMoney(double money)
    {
        this.money = this.money + money;
    }

    public void removeMoney(double money)
    {
        this.money = this.money - money;
    }

    public void infect()
    {
        isInfected = true;
    }

    public int getLocation()
    {
        return location;
    }

    public int getId()
    {
        return id;
    }

    public String getColor()
    {
        return color;
    }

    public String getPawn()
    {
        return pawn;
    }

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

    public String getName()
    {
        return name;
    }

    public boolean isInQuarantine()
    {
        return isInQuarantine;
    }
}
