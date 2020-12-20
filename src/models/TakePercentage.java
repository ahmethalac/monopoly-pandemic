package models;

public class TakePercentage extends ContiuousOffer {
    // properties
    private City city;
    private int percentage;

    // constructor
    public TakePercentage(City city, int percentage){
        this.city = city;
        this.percentage = percentage;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        // get percentage of rent from secondPlayers city
        double rate = ((double)percentage)/10;
        firstPlayer.addMoney(city.getRent() * rate);
        secondPlayer.addMoney(city.getRent() * (1 - rate));
    }

    @Override
    public City getCity(){
        return city;
    }

    @Override
    public String toString() {
        return "TakePercentage: " + city + " Percentage: " + percentage;
    }
}
