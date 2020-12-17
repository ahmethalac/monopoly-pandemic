package models;

import controllers.modelcontrollers.GameManager;

public class ChanceRegion extends Region {
    public ChanceRegion(int id){
        super(id);
    }
    public void performRegionAction() {
        GameManager.getInstance().pickChanceCard();
    }
}
