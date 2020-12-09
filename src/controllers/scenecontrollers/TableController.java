package controllers.scenecontrollers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import storage.filemanager.MeshImporter;

public class TableController extends SubScene {
    private final Group sceneItems;

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

        //This part is for debug purposes - will be deleted later
        Box box = new Box(50,50,50);
        box.translateZProperty().set(-100);
        box.translateXProperty().set(500);
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> System.out.println("Box is clicked"));
        sceneItems.getChildren().add(box);

        Node[] astronaut = MeshImporter.getPlayer();
        for ( Node node : astronaut){
            node.translateZProperty().set(-100);
            node.translateYProperty().set(-900);
        }
        sceneItems.getChildren().addAll(astronaut);
    }

    private void addLight() {
        AmbientLight light = new AmbientLight();
        light.translateZProperty().set(-500);
        sceneItems.getChildren().add(light);
    }

    private void addCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.translateZProperty().set(-800);
        camera.translateYProperty().set(1500);
        camera.getTransforms().add(new Rotate(60, Rotate.X_AXIS));
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
