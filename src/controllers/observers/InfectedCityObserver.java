package controllers.observers;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import models.City;
import storage.filemanager.MeshImporter;

import java.util.ArrayList;

public class InfectedCityObserver extends Observer {

    private ArrayList<MeshView> viruses = new ArrayList<>();
    private int[] coordinates;
    private Group sceneItems;

    public InfectedCityObserver(City city, int[] coordinates, Group sceneItems){
        this.subject = city;
        this.subject.attach(this);
        this.coordinates = coordinates;
        this.sceneItems = sceneItems;
    }

    @Override
    public void update() {
        if ( sceneItems != null) {
            if (((City) subject).isInfected()) {
                MeshView virus = MeshImporter.getVirus();
                virus.setTranslateX(coordinates[0]);
                virus.setTranslateY(coordinates[1]);
                sceneItems.getChildren().add(virus);
            } else {
                sceneItems.getChildren().removeAll(viruses);
            }
        }
    }
}
