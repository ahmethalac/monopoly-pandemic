package controllers.observers;

import controllers.modelcontrollers.GameManager;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import models.Player;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;

public class InfectedPlayerObserver extends Observer {

    private final MeshView pawn;

    public InfectedPlayerObserver(Player player, MeshView pawn){
        this.subject = player;
        this.subject.attach(this);
        this.pawn = pawn;
    }

    @Override
    public void update() {
        if ( ((Player) subject).isInfected() ){
            pawn.setMaterial(new PhongMaterial(Color.rgb(102,204,0)));
        } else {
            pawn.setMaterial(new PhongMaterial(Color.BLACK));
        }
    }
}
