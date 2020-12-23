package models;

public class TakePercentage extends ContiuousOffer {
    // properties
    private City city;
    private int percentage;
    private Player owner;

    // constructor
    public TakePercentage(City city, int percentage, Player owner){
        this.city = city;
        this.percentage = percentage;
        this.owner = owner;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        // get percentage of rent from secondPlayers city
        double rate = ((double)percentage)/100;
        if(owner.getId() == firstPlayer.getId()){
            secondPlayer.addMoney(city.getRent() * rate);
            firstPlayer.addMoney(city.getRent() * (1 - rate));
        }
        else {
            firstPlayer.addMoney(city.getRent() * rate);
            secondPlayer.addMoney(city.getRent() * (1 - rate));
        }
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
