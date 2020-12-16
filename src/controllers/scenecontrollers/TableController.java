package controllers.scenecontrollers;

import controllers.modelcontrollers.GameManager;
import controllers.observers.BuildingObserver;
import controllers.observers.ColorObserver;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import models.*;
import storage.filemanager.MeshImporter;
import storage.filemanager.SettingImporter;
import utils.RegionList;

import java.util.ArrayList;
import java.util.List;

public class TableController extends SubScene {
    private GameSceneController gameSceneController;
    private final Group sceneItems;
    private ArrayList<RegionList> regionsList = new ArrayList<>();
    List<int[]> coordinates = SettingImporter.getRegionCoordinates();
    private final MeshView[] pawn; //Experimental

    public TableController(GameSceneController gameSceneController) {
        super(new Group(),
                SceneManager.getInstance().getWidth(),
                SceneManager.getInstance().getHeight(),
                true,
                SceneAntialiasing.DISABLED);
        this.gameSceneController = gameSceneController;
        this.setFill(Color.SILVER);
        sceneItems = (Group) this.getRoot();

        addCamera();
        addLight();

        sceneItems.getChildren().addAll(MeshImporter.getTable());

        initializeRegions();

        //Experimental
        MeshView[] astronaut = MeshImporter.getPlayer();
        for ( MeshView node : astronaut){
            node.translateZProperty().set(-100);
            node.translateYProperty().set(-800);
        }
        sceneItems.getChildren().addAll(astronaut);

        pawn = MeshImporter.getPawn("Blue");
        for ( MeshView part : pawn){
            part.setTranslateX(coordinates.get(0)[0]);
            part.setTranslateY(coordinates.get(0)[1]);
        }
        sceneItems.getChildren().addAll(pawn);
    }

    public void rotateTable(){
        rotateAroundCenter(this.getCamera(), 90);
        //Experimental
        for ( MeshView part : pawn){
            RotateTransition transition = new RotateTransition(Duration.seconds(1.5), part);
            transition.setByAngle(90);
            transition.play();
        }

        ((City)GameManager.getInstance().getRegions().get(1)).setOwner(new Player("ahmet","Red","a",2));
        ((City)GameManager.getInstance().getRegions().get(2)).setOwner(new Player("ahmet","Blue","a",2));
        ((City)GameManager.getInstance().getRegions().get(3)).setOwner(new Player("ahmet","Pink","a",2));
        ((City)GameManager.getInstance().getRegions().get(4)).setOwner(new Player("ahmet","Green","a",2));
        ((City)GameManager.getInstance().getRegions().get(5)).setOwner(new Player("ahmet","Yellow","a",2));
        ((City)GameManager.getInstance().getRegions().get(6)).setOwner(new Player("ahmet","Orange","a",2));
        ((City)GameManager.getInstance().getRegions().get(7)).setOwner(new Player("ahmet","Purple","a",2));
        ((City)GameManager.getInstance().getRegions().get(8)).setOwner(new Player("ahmet","Cyan","a",2));
    }

    private void initializeRegions() {
        ArrayList<Region> regions = GameManager.getInstance().getRegions();

        for ( int i = 0; i < coordinates.size(); i++){
            MeshView region = MeshImporter.getRegion();
            region.translateXProperty().set(coordinates.get(i)[0]);
            region.translateYProperty().set(coordinates.get(i)[1]);
            region.translateZProperty().set(0);

            int finalI = i;

            RegionList group = new RegionList(region, sceneItems, regions.get(i), mouseEvent -> {
                if ( regions.get(finalI) instanceof City){
                    gameSceneController.handleCityPopup((City) regions.get(finalI));
                } else{
                    //Debug Purposes
                    System.out.println(regions.get(finalI).getClass().getName());
                }
            });
            regionsList.add(group);

            if ( regions.get(i) instanceof City){
                region.setMaterial(new PhongMaterial(Color.GREY));
                new ColorObserver(regions.get(i), group);
                new BuildingObserver(regions.get(i), group);
            } else if ( regions.get(i) instanceof ChanceRegion){
                region.setMaterial(new PhongMaterial(Color.BLACK));
                group.add(getQuestionMark(coordinates.get(i)[0], coordinates.get(i)[1]));
            } else if ( regions.get(i) instanceof PirateRegion){
                region.setMaterial(new PhongMaterial(Color.BLACK));
                group.add(getPirate(coordinates.get(i)[0], coordinates.get(i)[1]));
            } else if ( regions.get(i) instanceof StartingRegion){
                region.setMaterial(new PhongMaterial(Color.WHITE));
            }

        }
    }

    private void addLight() {
        LightBase ambient = new AmbientLight();
        ambient.translateZProperty().set(-1000);
        sceneItems.getChildren().add(ambient);
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

    private MeshView getPirate(int x, int y){
        MeshView pirate = MeshImporter.getPirate();
        pirate.setTranslateX(x);
        pirate.setTranslateY(y);

        setAnimation(pirate);

        return pirate;
    }

    private MeshView getQuestionMark(int x, int y){
        MeshView questionMark = MeshImporter.getQuestionMark();
        questionMark.setTranslateX(x);
        questionMark.setTranslateY(y);

        setAnimation(questionMark);

        return questionMark;
    }
    private void setAnimation(Node node){
        Timeline rotation = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(node.rotateProperty(), 360))
        );
        rotation.setCycleCount(Timeline.INDEFINITE);
        rotation.play();

        Timeline translation = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(node.translateZProperty(), -10, Interpolator.EASE_BOTH))
        );
        translation.setAutoReverse(true);
        translation.setCycleCount(Timeline.INDEFINITE);
        translation.play();
    }
}
