package storage.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SettingImporter {
    private static final String filePath = System.getProperty("user.dir") + "/src/gameSettings/";

    public static List<int[]> getRegionCoordinates(){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath + "coordinates"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        ArrayList<int[]> coordinates = new ArrayList<>();
        while ( scanner.hasNext()){
            String[] stringCoordinate = scanner.nextLine().split(",");
            int[] coordinate = new int[stringCoordinate.length];
            for ( int i = 0; i < stringCoordinate.length; i++){
                coordinate[i] = Integer.parseInt(stringCoordinate[i]);
            }
            coordinates.add(coordinate);
        }
        return coordinates;
    }
}
