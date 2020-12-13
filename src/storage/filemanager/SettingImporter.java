package storage.filemanager;

import models.*;

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

    public static ArrayList<Region> getRegions(){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath + "Regions"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        ArrayList<Region> regions = new ArrayList<>();
        int id = 1;
        while ( scanner.hasNext()){
            String[] stringRegions = scanner.nextLine().split(",");
            Region newRegion;
            if(stringRegions[0].equals("0")){
                double price = Double.parseDouble(stringRegions[2]);
                double[] rents = { Double.parseDouble(stringRegions[3])
                        , Double.parseDouble(stringRegions[3])
                        , Double.parseDouble(stringRegions[3])
                        , Double.parseDouble(stringRegions[3])
                        , Double.parseDouble(stringRegions[3]) };
                String name = stringRegions[1];
                newRegion = new City(price,rents,name,id);
            }
            else if(stringRegions[0].equals("1")){
                newRegion = new ChanceRegion(id);
            }
            else if(stringRegions[0].equals("2")){
                newRegion = new PirateRegion(id);
            }
            else{
                newRegion = new StartingRegion(id);
            }
            regions.add(newRegion);
            id++;
        }
        return regions;
    }
}
