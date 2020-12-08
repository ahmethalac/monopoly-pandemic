package storage.models;

public class SellRegion extends OneTimeOffer {
    // properties
    private City city;

    // constructor
    public SellRegion(City city){
        this.city = city;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        // add city to second player (if possible)
        secondPlayer.addCity(city.getName());
        // remove city from first player (if possible)
        firstPlayer.removeCity(city.getName());
    }
}
