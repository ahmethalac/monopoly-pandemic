package controllers.observers;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import models.City;
import models.Observable;
import utils.RegionList;
import utils.Texture;

import static utils.ColorUtil.getLightColor;

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
                material.setDiffuseMap(Texture.getTexture(color));
                region.get(i).setMaterial(material);
            }
        }
    }
}
