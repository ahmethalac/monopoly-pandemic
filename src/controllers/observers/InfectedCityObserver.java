package controllers.observers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import models.City;
import storage.filemanager.MeshImporter;

import java.sql.Time;
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
                if (viruses.isEmpty()) {
                    int angle = 0;
                    while ( angle <= 360 ){
                        MeshView virus = MeshImporter.getVirus();
                        double yOffset = 35 * Math.cos(angle * Math.PI / 180);
                        double xOffset = 35 * Math.sin(angle * Math.PI / 180);

                        virus.setTranslateX(coordinates[0] + xOffset);
                        virus.setTranslateY(coordinates[1] + yOffset);

                        Rotate rotate = new Rotate();
                        rotate.setPivotX(-xOffset);
                        rotate.setPivotY(-yOffset);

                        virus.getTransforms().add(rotate);
                        Timeline timeline = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                new KeyFrame(Duration.seconds(3), new KeyValue(rotate.angleProperty(), 360)));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();

                        sceneItems.getChildren().add(virus);
                        viruses.add(virus);
                        angle += 90;
                    }
                }
            } else {
                sceneItems.getChildren().removeAll(viruses);
                viruses.clear();
            }
        }
    }
}
