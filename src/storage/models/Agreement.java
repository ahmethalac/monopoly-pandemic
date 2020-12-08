package storage.models;

public class Agreement {
    private Player firstPlayer;
    private Player secondPlayer;
    private Offer firstOffer;
    private Offer secondOffer;
    private boolean isAccepted = false;
    private boolean isOffered = false;
    private int id;
    private boolean isEmpty = false;

    public Agreement(Offer firstOffer, Offer secondOffer, Player firstPlayer, Player secondPlayer){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstOffer = firstOffer;
        this.secondOffer = secondOffer;
    }

    private void performOneTimeOffer(){
        if(firstOffer instanceof OneTimeOffer && secondOffer instanceof OneTimeOffer){
            firstOffer.performOffer(firstPlayer, secondPlayer);
            secondOffer.performOffer(secondPlayer, firstPlayer);
            firstOffer = null;
            secondOffer = null;
            isEmpty = true;
            return;
        }
        if(firstOffer instanceof OneTimeOffer) {
            firstOffer.performOffer(firstPlayer, secondPlayer);
            firstOffer = null;
        }
        if(secondOffer instanceof OneTimeOffer) {
            secondOffer.performOffer(secondPlayer, firstPlayer);
            secondOffer = null;
        }
    }

    // check offers if they are continuous
    public boolean checkOffer(City city){
        if(isAccepted){
            // if firstOffer instanceof ContiuousOffer
            if(firstOffer != null){
                if(city.getId() == ((ContiuousOffer) firstOffer).getCity().getId()){
                    firstOffer.performOffer(firstPlayer, secondPlayer);
                }
            }
            // if secondOffer instanceof ContiuousOffer
            if(secondOffer != null){
                if(city.getId() == ((ContiuousOffer) secondOffer).getCity().getId()){
                    secondOffer.performOffer(secondPlayer, firstPlayer);
                }
            }
        }
        return isAccepted;
    }

    // getter method for isEmpty prop
    public boolean isEmpty(){
        return isEmpty;
    }

    // return if second player knows about the agreement
    public boolean isOffered(){
        return isOffered;
    }

    // accept agreement
    public void accept(){
        isAccepted = true;
        isOffered = true;
        performOneTimeOffer();
    }

    // get first player
    public Player getFirstPlayer(){
        return firstPlayer;
    }

}
