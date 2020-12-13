package controllers.observers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import storage.models.City;
import storage.models.Observable;

import java.util.ArrayList;

public class ColorObserver extends Observer {

    private final ArrayList<Node> region;

    public ColorObserver(Observable subject, ArrayList<Node> region){
        this.subject = subject;
        this.subject.attach(this);
        this.region = region;
    }

    @Override
    public void update() {
        for ( Node node : region){
            Color color = CreateGameSceneController.getFXColor(((City)subject).getOwner().getColor());
            if ( node instanceof Box ){

            }else{
                ((Shape3D) node).setMaterial(new PhongMaterial(color));
            }
        }
    }
}
