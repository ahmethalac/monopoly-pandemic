package models;

import controllers.modelcontrollers.GameManager;

public class StartingRegion extends Region{
        private final int STARTING_REGION_BONUS = 25000;
        public StartingRegion(int id){ super(id); }
        public void performRegionAction() {
                Player currentPlayer = GameManager.getInstance().getCurrentPlayer();
                currentPlayer.addMoney(STARTING_REGION_BONUS);
        }
}
