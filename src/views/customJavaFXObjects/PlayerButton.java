package views.customJavaFXObjects;

import javafx.scene.control.Button;
import models.Player;

public class PlayerButton extends Button {

    private final Player player;

    public PlayerButton(Player p, String s) {
        super(s);
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }
}