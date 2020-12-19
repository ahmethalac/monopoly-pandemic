package models;

import controllers.modelcontrollers.GameManager;

public class TestRegion extends Region {
    public TestRegion(int id){
        super(id);
    }
    public void performRegionAction() {
        GameManager.getInstance().getCurrentPlayer().quarantine(true);
    }
}
