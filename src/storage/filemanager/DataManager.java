package storage.filemanager;

import controllers.modelcontrollers.GameManager;
import models.Game;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    // properties
    private static DataManager dataManager = null;
    private String saveGameFolder;

    // constructor
    public DataManager(){
        String currentDir = System.getProperty("user.dir");
        this.saveGameFolder = currentDir + "/saves/";
    }

    // methods
    public static DataManager getInstance(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void saveGame(String saveName){
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

    public void loadGame(String saveName){
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
    public List<String> getSavedNames(){
        List<String> savedNames;
        //Creating a File object for directory
        File directoryPath = new File(this.saveGameFolder);

        try{
            String[] temp = directoryPath.list();
            if(temp == null){
                return null;
            }
            savedNames = Arrays.asList(temp);
            for ( int i=0; i<savedNames.size(); i++ ) {
                savedNames.set(i, savedNames.get(i).substring(0, savedNames.get(i).lastIndexOf(".")));
            }
            return savedNames;
        }
        catch(Exception e){
            System.err.println(e.toString());
            return null;
        }
    }

}
