package controllers.observers;

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
        controller.renderPlayer((Player) subject);
    }
}
