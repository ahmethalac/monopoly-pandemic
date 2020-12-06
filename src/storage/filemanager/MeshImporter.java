package storage.filemanager;

import com.interactivemesh.jfx.importer.ModelImporter;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.Node;

public class MeshImporter {
    private static final String currentDir = System.getProperty("user.dir") + "/src/assets/3Dmodels/";
    private static final ModelImporter importer = new ObjModelImporter();

    private static Node[] getNodeArray(String fileName) {
        importer.read(currentDir + fileName);
        return (Node[]) importer.getImport();
    }

    public static Node[] getTable(){
        return getNodeArray("Table.obj");
    }
}
