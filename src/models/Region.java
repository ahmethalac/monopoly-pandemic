package models;

public abstract class Region extends Observable {
    private int id;

    public Region(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
