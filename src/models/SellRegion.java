package models;

public class SellRegion extends OneTimeOffer {
    // properties
    private City city;
    private Player owner;

    // constructor
    public SellRegion(City city, Player owner){
        this.city = city;
        this.owner = owner;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        if(owner.getId() == firstPlayer.getId()){
            // remove city from second player (if possible)
            if(firstPlayer.removeCity(city)){
                // add city to first player (if possible)
                secondPlayer.addCity(city);
                city.setOwner(secondPlayer);
                return;
            }
            System.out.println("First player does not own city: " + city.getName());
        }else{
            // remove city from second player (if possible)
            if(secondPlayer.removeCity(city)){
                // add city to first player (if possible)
                firstPlayer.addCity(city);
                city.setOwner(firstPlayer);
                return;
            }
            System.out.println("Second player does not own city: " + city.getName());
        }
    }

    @Override
    public String toString() {
        return "SellRegion: " + city;
    }
}
