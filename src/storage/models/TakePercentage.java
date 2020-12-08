package storage.models;

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
    }

    @Override
    public City getCity(){
        return city;
    }
}
