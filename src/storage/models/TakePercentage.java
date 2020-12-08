package storage.models;

public class TakePercentage extends ContiuousOffer {
    // properties
    private City city;
    private double percentage;

    // constructor
    public TakePercentage(City city, double percentage){
        this.city = city;
        this.percentage = percentage;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        // get percentage of rent from secondPlayers city
        firstPlayer.addMoney(city.getRent() * percentage);
        secondPlayer.addMoney(city.getRent() * (1 - percentage));
    }

    @Override
    public City getCity(){
        return city;
    }
}
