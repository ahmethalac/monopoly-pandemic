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
        // remove city from second player (if possible)
        if(secondPlayer.removeCity(city)){
            // add city to first player (if possible)
            firstPlayer.addCity(city);
            return;
        }
        System.out.println("Second player does not own city: " + city.getName());
    }
}
