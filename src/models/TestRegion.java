package models;

import controllers.modelcontrollers.GameManager;

public class TestRegion extends Region {
    public TestRegion(int id){
        super(id);
    }
    public void performRegionAction() {
        if(GameManager.getInstance().getCurrentPlayer().isInfected()){
            // get him/her in quarantine
            GameManager.getInstance().getCurrentPlayer().quarantine(true);
        }
    }
}
