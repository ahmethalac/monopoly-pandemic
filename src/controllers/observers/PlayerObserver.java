package controllers.observers;

import controllers.modelcontrollers.GameManager;
import controllers.scenecontrollers.GameSceneController;
import models.Player;

public class PlayerObserver extends Observer{

    private GameSceneController controller;

    public PlayerObserver(Player player, GameSceneController controller){
        this.subject = player;
        this.subject.attach(this);
        this.controller = controller;
    }
    @Override
    public void update() {
        if (GameManager.getInstance().getCurrentPlayer() == subject){
            controller.renderPlayer((Player) subject);
        }
    }
}
