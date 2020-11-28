package storage.filemanager;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DataManager {
    // properties
    private static DataManager dataManager = null;
    private String saveGameFolder;
    private List<String> savedNames;

    // methods
    public static DataManager getInstance(){
        if(dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public void saveGame(){
        //TODO
    }

    public void loadGame(String saveName){
        //TODO
    }

    /*
     * gets filenames of saves in a List
     * @return the List of saved game file names, null if directory path is wrong or caught an exception
     */
    public List<String> getSavedNames(){
        List<String> savedNames;
        String currentDir = System.getProperty("user.dir");
        this.saveGameFolder = currentDir + "/saves/";
        //Creating a File object for directory
        File directoryPath = new File(this.saveGameFolder);

        try{
            String[] temp = directoryPath.list();
            if(temp == null){
                return null;
            }
            savedNames = Arrays.asList(temp);
            this.savedNames = savedNames;
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
