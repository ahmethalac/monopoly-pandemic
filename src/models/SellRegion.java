package models;

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
            city.setOwner(firstPlayer);
            return;
        }
        System.out.println("Second player does not own city: " + city.getName());
    }

    @Override
    public String toString() {
        return "SellRegion: " + city;
    }
}
