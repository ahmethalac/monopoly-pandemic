package models;

public class GiveMoney extends OneTimeOffer {
    private double money;
    private Player giver;

    public GiveMoney(double money, Player giver){
        this.giver = giver;
        this.money = money;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        if(giver.getId() == firstPlayer.getId()){
            //first player gives specified amount of money to second player
            firstPlayer.removeMoney(money);
            //second player takes specified amount of money from the first player
            secondPlayer.addMoney(money);
        }
        else{
            //secondPlayer gives specified amount of money to second player
            secondPlayer.removeMoney(money);
            //firstPlayer takes specified amount of money from the first player
            firstPlayer.addMoney(money);
        }

    }

    @Override
    public String toString() {
        return "Give" + money;
    }
}
