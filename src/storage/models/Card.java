package storage.models;

import java.awt.*;

public class Card {
    private int id;
    private String cardName;
    private Image cardImg;

    public Card(int id, String cardName, Image cardImg){
        this.id = id;
        this.cardName = cardName;
        this.cardImg = cardImg;
    }

    public int getId(){
        return id;
    }

    public String getCardName() {
        return cardName;
    }

    public Image getCardImg() {
        return cardImg;
    }
}
