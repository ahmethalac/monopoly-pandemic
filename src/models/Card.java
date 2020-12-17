package models;

import java.io.Serializable;

public interface Card extends Serializable {

    //public int getId();

    //public String getCardName();

    //public Image getCardImg();

    void executeAction(Game game);
}
