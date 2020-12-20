package controllers.observers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    private VBox gameLog;
    private boolean prevInfected;

    public InfectedCityObserver(City city, int[] coordinates, Group sceneItems, VBox gameLog){
        this.subject = city;
        this.subject.attach(this);
        this.coordinates = coordinates;
        this.sceneItems = sceneItems;
        this.gameLog = gameLog;
        this.prevInfected = false;
    }

    @Override
    public void update() {
        if ( sceneItems != null) {
            if (((City) subject).isInfected() && !prevInfected) {
                    Text text = new Text(((City) subject).getName() + " is infected!");
                    text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                    if ( gameLog.getChildren().size() > 5) {
                        gameLog.getChildren().subList(0, gameLog.getChildren().size() - 5).clear();
                    }
                    gameLog.getChildren().add(text);
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
                    prevInfected = true;
            }
            if ( !((City) subject).isInfected() && prevInfected){
                sceneItems.getChildren().removeAll(viruses);
                viruses.clear();
                Text text = new Text(((City) subject).getName() + " is disinfected from virus!");
                text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                if ( gameLog.getChildren().size() > 5) {
                    gameLog.getChildren().subList(0, gameLog.getChildren().size() - 5).clear();
                }
                gameLog.getChildren().add(text);
                prevInfected = false;
            }
        }
    }
}
