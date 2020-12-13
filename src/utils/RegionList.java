package utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape3D;
import models.City;

import java.util.ArrayList;

public class RegionList extends ArrayList<Shape3D> {

    private final EventHandler<MouseEvent> eventHandler;

    public RegionList(EventHandler<MouseEvent> eventHandler) {
        super();
        this.eventHandler = eventHandler;
    }

    @Override
    public boolean add(Shape3D shape3D) {
        if (super.add(shape3D)){
            shape3D.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        }
        return false;
    }
}
