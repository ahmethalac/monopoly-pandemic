package controllers.observers;

import controllers.modelcontrollers.GameManager;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.MeshView;
import javafx.util.Duration;
import models.Player;
import storage.filemanager.SettingImporter;

import java.util.ArrayList;

public class LocationObserver extends Observer {

    private final MeshView[] pawn;
    private final ArrayList<int[]> coordinates;
    //How many pawns are there for each region
    private static final ArrayList<int[]> pawnCounts = new ArrayList<>();
    private int[] prevLocation;
    private final int playerCount;

    public LocationObserver(Player player, MeshView[] pawn){
        this.subject = player;
        this.subject.attach(this);
        this.pawn = pawn;
        prevLocation = new int[]{player.getLocation(), 0};
        coordinates = (ArrayList<int[]>) SettingImporter.getRegionCoordinates();
        playerCount = GameManager.getInstance().getPlayers().size();
        LocationObserver.initializePawnCounts(coordinates.size());
    }

    @Override
    public void update() {
        int location = ((Player) subject).getLocation();
        if ( prevLocation[0] != location) {
            int emptySlot = findEmptyLocation(location);
            pawnCounts.get(location)[emptySlot] = 1;
            pawnCounts.get(prevLocation[0])[prevLocation[1]] = 0;

            double[] offsets = getOffset(emptySlot);

            for (MeshView part : pawn) {
                TranslateTransition horizontalMove = new TranslateTransition(Duration.seconds(1), part);
                horizontalMove.setToX(coordinates.get(location)[0] + offsets[0]);
                horizontalMove.setToY(coordinates.get(location)[1] + offsets[1]);
                horizontalMove.play();

                TranslateTransition verticalMove = new TranslateTransition(Duration.seconds(0.5), part);
                verticalMove.setAutoReverse(true);
                verticalMove.setCycleCount(2);
                verticalMove.setByZ(-50);
                verticalMove.play();
            }
            prevLocation = new int[]{location, emptySlot};
        }
    }

    public static double[] getOffset(int emptySlot){
        double xOffset = 20 * Math.cos(((emptySlot) + 1) * Math.PI / 4);
        double yOffset = 20 * Math.sin(((emptySlot) + 1) * Math.PI / 4);

        return new double[]{xOffset, yOffset};
    }

    private static void initializePawnCounts(int size){
        if ( pawnCounts.isEmpty()){
            for ( int i = 0; i < size; i++){
                pawnCounts.add(new int[]{0,0,0,0,0,0,0,0});
            }
        }
    }

    private int findEmptyLocation(int location){
        for ( int i = 0; i < pawnCounts.get(location).length; i += 8 / playerCount){
            if ( pawnCounts.get(location)[i] == 0){
                return i;
            }
        }
        return 0;
    }
}
