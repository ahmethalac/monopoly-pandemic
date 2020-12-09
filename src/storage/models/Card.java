package storage.models;

import java.awt.*;

public interface Card {

    public int getId();

    public String getCardName();

    public Image getCardImg();

    public void executeAction();
}
