package models;

import controllers.modelcontrollers.GameManager;

public class PirateRegion extends Region {
    private final int PIRATE_FEE = 2000;

    public PirateRegion(int id){ super(id); }
    public void performRegionAction() {
        GameManager.getInstance().getCurrentPlayer().removeMoney(PIRATE_FEE);
    }
}
