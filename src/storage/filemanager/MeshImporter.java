package storage.filemanager;

import com.interactivemesh.jfx.importer.ModelImporter;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.shape.MeshView;

public class MeshImporter {
    private static final String currentDir = System.getProperty("user.dir") + "/src/assets/3Dmodels/";
    private static final ModelImporter importer = new ObjModelImporter();

    private static MeshView[] getNodeArray(String fileName) {
        importer.read(currentDir + fileName);
        return (MeshView[]) importer.getImport();
    }

    public static MeshView[] getTable(){
        return getNodeArray("Table.obj");
    }

    public static MeshView[] getPlayer(){
        return getNodeArray("astronaut.obj");
    }

    public static MeshView getRegion() {
        return getNodeArray("Region.obj")[0];
    }
}
