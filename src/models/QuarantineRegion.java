package models;

import controllers.modelcontrollers.GameManager;

public class QuarantineRegion extends Region{
    public QuarantineRegion(int id){ super(id); }
    public void performRegionAction() {
        if(!GameManager.getInstance().getCurrentPlayer().isInQuarantine()) {
            GameManager.getInstance().getCurrentPlayer().quarantine(true);
            GameManager.getInstance().getCurrentPlayer().infect(true);
        }
    }
}
