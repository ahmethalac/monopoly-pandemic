package controllers.scenecontrollers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import storage.filemanager.MeshImporter;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;

public class TableController extends SubScene {
    private final Group sceneItems;
    private ArrayList<Node> regions = new ArrayList<>();

    public TableController() {
        super(new Group(),
                SceneManager.getInstance().getWidth(),
                SceneManager.getInstance().getHeight(),
                true,
                SceneAntialiasing.DISABLED);
        this.setFill(Color.SILVER);
        sceneItems = (Group) this.getRoot();

        addCamera();
        addLight();

        sceneItems.getChildren().addAll(MeshImporter.getTable());

        SettingImporter.getRegionCoordinates().forEach(ints -> {
            MeshView region = MeshImporter.getRegion();
            region.translateXProperty().set(ints[0]);
            region.translateYProperty().set(ints[1]);
            region.translateZProperty().set(0);

            regions.add(region);
            sceneItems.getChildren().add(region);

            //Experimental
            region.setMaterial(new PhongMaterial(Color.LAWNGREEN));
            region.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println(region.getTranslateX());
                System.out.println(region.getTranslateY());
            });
        });

        //Experimental
        MeshView[] astronaut = MeshImporter.getPlayer();
        for ( MeshView node : astronaut){
            node.translateZProperty().set(-100);
            node.translateYProperty().set(-900);
        }
        sceneItems.getChildren().addAll(astronaut);

        Box box = new Box(10,10,10);
        box.translateXProperty().set(-278);
        box.translateYProperty().set(157);
        box.translateZProperty().set(-5);
        box.setMaterial(new PhongMaterial(Color.YELLOW));
        sceneItems.getChildren().add(box);
    }

    private void addLight() {
        AmbientLight light = new AmbientLight();
        light.translateZProperty().set(-500);
        sceneItems.getChildren().add(light);
    }

    private void addCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.translateZProperty().set(-900);
        camera.translateYProperty().set(1300);
        camera.getTransforms().add(new Rotate(50, Rotate.X_AXIS));
        camera.setNearClip(1);
        camera.setFarClip(3000);
        this.setCamera(camera);
    }

    private void rotateAroundCenter(Node node, double angle){
        double x = node.getTranslateX();
        double y = node.getTranslateY();
        double rotate = node.getRotate();
        System.out.println(x);
        System.out.println(y);
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.translateYProperty(), x * Math.sin(angle * Math.PI / 180) + y * Math.cos(angle * Math.PI / 180)))
        );
        timeline2.play();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.translateXProperty(), x * Math.cos(angle * Math.PI / 180) - y * Math.sin(angle * Math.PI / 180)))
        );
        timeline.play();

        Timeline timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.rotateProperty(), rotate + angle))
        );
        timeline3.play();
    }

    public void rotateTable(){
        rotateAroundCenter(this.getCamera(), 90);
    }
}
