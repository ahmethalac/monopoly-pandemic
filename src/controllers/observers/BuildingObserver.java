package controllers.observers;

import models.City;
import models.Observable;
import utils.RegionList;

public class BuildingObserver extends Observer {

    private final RegionList region;

    public BuildingObserver(Observable subject, RegionList region){
        this.subject = subject;
        this.subject.attach(this);
        this.region = region;
    }

    @Override
    public void update() {
        region.setBuilding(((City) subject).getNumberOfBuildings());
    }
}
