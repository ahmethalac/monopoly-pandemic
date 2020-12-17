package models;

import java.io.Serializable;

public abstract class Region extends Observable implements Serializable {
    private int id;

    public Region(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
