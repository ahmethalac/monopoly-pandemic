package models;

import controllers.modelcontrollers.GameManager;
import javafx.application.Platform;

public class TestRegion extends Region {
    public TestRegion(int id){
        super(id);
    }

    public void runPerformRegionAction() {
        Platform.runLater(this::performRegionAction);
    }

    private void performRegionAction() {
        GameManager.getInstance().getCurrentPlayer().quarantine(true);
    }
}
