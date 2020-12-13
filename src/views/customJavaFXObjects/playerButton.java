package views.customJavaFXObjects;

import javafx.scene.control.Button;
import models.Player;

public class playerButton extends Button {

    private final Player player;

    public playerButton(Player p, String s) {
        super(s);
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }
}