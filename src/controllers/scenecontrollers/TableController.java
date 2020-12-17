package controllers.scenecontrollers;

import controllers.modelcontrollers.GameManager;
import controllers.observers.BuildingObserver;
import controllers.observers.ColorObserver;
import controllers.observers.LocationObserver;
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
import utils.RotationUtil;

import java.util.ArrayList;
import java.util.List;

public class TableController extends SubScene {
    private final GameSceneController gameSceneController;
    private final Group sceneItems;
    private final ArrayList<RegionList> regionsList = new ArrayList<>();
    private final ArrayList<int[]> coordinates = (ArrayList<int[]>) SettingImporter.getRegionCoordinates();
    private final ArrayList<MeshView[]> pawns = new ArrayList<>(); //Experimental

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

        ArrayList<Player> players = GameManager.getInstance().getPlayers();
        initializePawns(players);
        initializeAstronauts(players);
    }

    public void rotateTable(){
        double angle = (double)360 / GameManager.getInstance().getPlayers().size();
        RotationUtil.rotateAroundCenter(this.getCamera(), angle);
        for ( MeshView[] pawn : pawns ){
            for ( MeshView part : pawn){
                RotateTransition transition = new RotateTransition(Duration.seconds(angle / 60), part);
                transition.setByAngle(angle);
                transition.play();
            }
        }
    }

    private void initializeAstronauts(ArrayList<Player> players) {
        double angle = 0;
        for ( Player player : players ){
            MeshView[] astronaut = MeshImporter.getPlayer(player.getColor());
            for ( MeshView node : astronaut){
                node.setTranslateZ(-100);
                node.setTranslateX(-750 * Math.sin(angle * Math.PI / 180));
                node.setTranslateY(750 * Math.cos(angle * Math.PI / 180));
                node.setRotate(angle - 180);
            }
            sceneItems.getChildren().addAll(astronaut);
            angle += (double)360 / players.size();
        }
    }

    private void initializePawns(ArrayList<Player> players) {
        int i = 0;
        for ( Player player : players ){
            MeshView[] pawn = MeshImporter.getPawn(player.getColor());
            double[] offsets = LocationObserver.getOffset(i);
            for ( MeshView part : pawn){
                part.setTranslateX(coordinates.get(0)[0] + offsets[0]);
                part.setTranslateY(coordinates.get(0)[1] + offsets[1]);
            }
            sceneItems.getChildren().addAll(pawn);
            pawns.add(pawn);
            new LocationObserver(player, pawn);
            i += 8 / players.size();
        }
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
        camera.translateZProperty().set(-1000);
        camera.translateYProperty().set(1400);
        camera.getTransforms().add(new Rotate(52, Rotate.X_AXIS));
        camera.setNearClip(1);
        camera.setFarClip(3000);
        this.setCamera(camera);
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
