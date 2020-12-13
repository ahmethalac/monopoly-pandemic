package controllers.observers;

import controllers.scenecontrollers.CreateGameSceneController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import models.City;
import models.Observable;
import utils.RegionList;

import java.util.ArrayList;

import static utils.colorUtil.getFXColor;
import static utils.colorUtil.getLightColor;

public class ColorObserver extends Observer {

    private final RegionList region;

    public ColorObserver(Observable subject, RegionList region){
        this.subject = subject;
        this.subject.attach(this);
        this.region = region;
    }

    @Override
    public void update() {
        for ( int i = 0; i < region.size(); i++){
            String color = ((City)subject).getOwner().getColor();
            if ( i == 0 ){
                region.get(i).setMaterial(new PhongMaterial(getLightColor(color)));
            } else {
                PhongMaterial material = new PhongMaterial();
                Image image = new Image(getClass().getResourceAsStream("../../assets/textures/" + color +".png"));
                material.setDiffuseMap(image);
                region.get(i).setMaterial(material);
            }
        }
    }
}
