package views.customJavaFXObjects;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import models.Player;

public class PlayerComboBox extends ComboBox<String> {

    private final Player player;

    public PlayerComboBox(ObservableList<String> observableList, Player player) {
        super(observableList);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
