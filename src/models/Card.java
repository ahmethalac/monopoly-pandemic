package models;

import java.io.Serializable;

public interface Card extends Serializable {

    //public int getId();

    public String getCardString();

    //public Image getCardImg();

    void executeAction(Game game);
}
