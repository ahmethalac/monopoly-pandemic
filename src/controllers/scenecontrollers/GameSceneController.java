package controllers.scenecontrollers;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import storage.filemanager.MeshImporter;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    private SubScene cameraScene;
    private final Group sceneItems = new Group();

    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraScene = new SubScene(sceneItems,
                SceneManager.getInstance().getWidth(),
                SceneManager.getInstance().getHeight(),
                true,
                SceneAntialiasing.DISABLED);
        cameraScene.setFill(Color.SILVER);

        addCamera();
        addLight();

        stackPane.getChildren().add(cameraScene);
        cameraScene.toBack();

        sceneItems.getChildren().addAll(MeshImporter.getTable());

        //This part is for debug purposes - will be deleted later
        Box box = new Box(50,50,50);
        box.translateZProperty().set(-100);
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> System.out.println("Box is clicked"));
        sceneItems.getChildren().add(box);
    }

    private void addLight() {
        AmbientLight light = new AmbientLight();
        light.translateZProperty().set(-500);
        sceneItems.getChildren().add(light);
    }

    private void addCamera() {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.translateZProperty().set(-1100);
        camera.translateYProperty().set(1250);
        camera.getTransforms().add(new Rotate(45, Rotate.X_AXIS));
        camera.setNearClip(1);
        camera.setFarClip(3000);
        cameraScene.setCamera(camera);
    }

    //For debug purposes
    public void rotateTable(ActionEvent actionEvent) {
        for ( Node node : sceneItems.getChildren()){
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), node);
            rotateTransition.setAxis(Rotate.Z_AXIS);
            rotateTransition.setByAngle(60);
            rotateTransition.play();
        }
    }
}
