package utils;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import models.City;
import models.Region;
import storage.filemanager.MeshImporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RegionList extends ArrayList<Shape3D> {

    private final EventHandler<MouseEvent> eventHandler;
    private final Region region;
    private Group sceneItems;
    private boolean hotelBuilt;

    public RegionList(MeshView regionBase, Group sceneItems, Region region, EventHandler<MouseEvent> eventHandler) {
        super();
        this.eventHandler = eventHandler;
        this.region = region;
        this.sceneItems = sceneItems;
        this.add(regionBase);
        hotelBuilt = false;
    }

    @Override
    public boolean add(Shape3D shape3D) {
        if (super.add(shape3D)){
            shape3D.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            sceneItems.getChildren().add(shape3D);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Shape3D> c) {
        for (Shape3D shape : c){
            if (!this.add(shape)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object shape3D) {
        if ( super.remove(shape3D)){
            ((Shape3D)shape3D).removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            sceneItems.getChildren().remove(shape3D);
            return true;
        }
        return false;
    }

    public boolean setBuilding(int number){
        int difference = number - (hotelBuilt ? 5 : (this.size() - 1)); //-1 for base region item
        if ( difference > 0 ){
            return addBuilding(difference);
        } else if ( difference < 0 ){
            return removeBuilding(-difference);
        }
        return false;
    }

    private boolean addBuilding(int number){
        if ( hotelBuilt ){
            return false;
        }
        for ( int i = 0; i < number; i++){
            addABuilding();
        }
        return true;
    }

    private void addABuilding(){
        String color = ((City)region).getOwner().getColor();
        if ( this.size() == 1){
            MeshView house = MeshImporter.getHouse();
            house.translateXProperty().set(this.get(0).getTranslateX());
            house.translateYProperty().set(this.get(0).getTranslateY());
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseMap(Texture.getTexture(color));
            house.setMaterial(material);
            this.add(house);
        } else if ( this.size() == 5){
            removeBuilding(4);
            MeshView[] hotel = MeshImporter.getHotel();
            for ( MeshView part : hotel){
                part.setTranslateX(this.get(0).getTranslateX());
                part.setTranslateY(this.get(0).getTranslateY());
            }
            this.addAll(Arrays.asList(hotel));
            hotelBuilt = true;
        } else{
            for ( int j = 1; j < this.size(); j++){
                this.get(j).setTranslateZ(this.get(j).getTranslateZ() - 12);
            }
            Box box = new Box(18, 13.5, 12);
            box.translateXProperty().set(this.get(0).getTranslateX());
            box.translateYProperty().set(this.get(0).getTranslateY());
            box.translateZProperty().set(-6);
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseMap(Texture.getTexture(color));
            box.setMaterial(material);
            this.add(box);
        }
    }

    private boolean removeBuilding(int number){
        if ( this.size() - number < 1){
            return false;
        }
        if ( hotelBuilt ){
            for ( int i = 0; i < 3; i++){
                this.remove(this.get(1));
            }
            hotelBuilt = false;
            addBuilding(4);
            removeBuilding(number - 1);
            return true;
        }
        for ( int i = 0; i < number; i++){
            this.remove(this.get(this.size() - 1));
            for ( int j = 1; j < this.size(); j++){
                this.get(j).setTranslateZ(this.get(j).getTranslateZ() + 12);
            }
        }
        return true;
    }
}
