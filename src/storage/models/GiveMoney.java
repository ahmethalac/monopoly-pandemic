package storage.models;

public class GiveMoney extends OneTimeOffer {
    private double money;

    public GiveMoney(double money){
        this.money = money;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        //first player gives specified amount of money to second player
        firstPlayer.removeMoney(money);
        //second player takes specified amount of money from the first player
        secondPlayer.addMoney(money);
    }
}
