package storage.models;

public abstract class Region {
    protected String name;
    protected int id;

    public Region(String name, int id){
        this.name = name;
        this.id = id;
    }
}
