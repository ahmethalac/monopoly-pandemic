package storage.models;

public class SpecialRegion extends Region {
    private String miniGame;

    public SpecialRegion(String name, int id, String miniGame){
        super(name,id);
        this.miniGame = miniGame;

    }
}
