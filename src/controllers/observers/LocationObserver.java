package controllers.observers;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.MeshView;
import javafx.util.Duration;
import models.Player;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;

public class LocationObserver extends Observer {

    private MeshView[] pawn;
    private final ArrayList<int[]> coordinates = (ArrayList<int[]>) SettingImporter.getRegionCoordinates();

    public LocationObserver(Player player, MeshView[] pawn){
        this.subject = player;
        this.subject.attach(this);
        this.pawn = pawn;
    }

    @Override
    public void update() {
        int location = ((Player) subject).getLocation();
        if ( pawn[0].getTranslateX() != coordinates.get(location)[0] && pawn[0].getTranslateX() != coordinates.get(location)[0]) {
            for (MeshView part : pawn) {
                TranslateTransition horizontalMove = new TranslateTransition(Duration.seconds(1), part);
                horizontalMove.setToX(coordinates.get(location)[0]);
                horizontalMove.setToY(coordinates.get(location)[1]);
                horizontalMove.play();

                TranslateTransition verticalMove = new TranslateTransition(Duration.seconds(0.5), part);
                verticalMove.setAutoReverse(true);
                verticalMove.setCycleCount(2);
                verticalMove.setByZ(-30);
                verticalMove.play();
            }
        }
    }
}
