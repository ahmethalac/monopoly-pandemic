package controllers.observers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import models.City;
import models.Observable;

import java.util.ArrayList;

import static utils.colorUtil.getFXColor;

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
            Color color = getFXColor(((City)subject).getOwner().getColor());
            if ( node instanceof Box ){

            }else{
                ((Shape3D) node).setMaterial(new PhongMaterial(color));
            }
        }
    }
}
