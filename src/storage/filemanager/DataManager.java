package storage.filemanager;

import controllers.modelcontrollers.GameManager;
import models.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    private static final String saveGameFolder = System.getProperty("user.dir") + "/saves/";

    // methods
    public static void saveGame(String saveName){
        try{
            FileOutputStream fout = new FileOutputStream(saveGameFolder + saveName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            // save game object
            out.writeObject(GameManager.getInstance().getGame());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void loadGame(String saveName){
        try{
            FileInputStream fin = new FileInputStream(saveGameFolder + saveName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fin);
            // save game object
            Game game = (Game)in.readObject();
            GameManager.getInstance().setGame(game);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * gets filenames of saves in a List
     * @return the List of saved game file names, null if directory path is wrong or caught an exception
     */
    public static ArrayList<String> getSavedNames(){
        ArrayList<String> savedNames = new ArrayList<>();
        //Creating a File object for directory
        File directoryPath = new File(saveGameFolder);

        try{
            String[] temp = directoryPath.list();
            if(temp == null) {
                return null;
            }
            for ( String content : temp ) {
                savedNames.add(content);
            }
            for ( int i=0; i<savedNames.size(); i++ ) {
                try{
                    savedNames.set(i, savedNames.get(i).substring(0, savedNames.get(i).lastIndexOf(".")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return savedNames;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * gets classNames of chance card classes in a List
     * @return the List of chance card classes file names, null if directory path is wrong or caught an exception
     */
    public static ArrayList<String> getChanceCardNames(){
        ArrayList<String> names = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        String folder = currentDir + "/src/models/chanceCards/";
        //Creating a File object for directory
        File directoryPath = new File(folder);

        try{
            String[] temp = directoryPath.list();
            if(temp == null){
                return null;
            }
            for ( String content : temp ) {
                names.add(content);
            }
            for ( int i=0; i<names.size(); i++ ) {
                names.set(i, names.get(i).substring(0, names.get(i).lastIndexOf(".")));
            }
            return names;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
