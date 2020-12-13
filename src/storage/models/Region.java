package storage.models;

public abstract class Region extends Observable {
    private String name;
    private int id;

    public Region(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
