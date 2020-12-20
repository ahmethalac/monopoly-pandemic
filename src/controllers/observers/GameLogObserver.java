package controllers.observers;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import models.Player;

public class GameLogObserver extends Observer {

    private VBox gameLog;

    public GameLogObserver(Player player, VBox gameLog){
        this.subject = player;
        this.subject.attach(this);
        this.gameLog = gameLog;
    }

    @Override
    public void update() {
        //This observer needs information about the change so this update is empty
    }

    public void update(String changeType, double amount){
        String log = "";
        String name = ((Player) subject).getName();
        switch (changeType){
            case "bankrupt":
                log = name + " is bankrupted!";
                break;
            case "quarantine":
                if ( amount == 0){
                    log = name + "'s quarantine is ended!";
                } else {
                    log = name + " goes to quarantine!";
                }
                break;
            case "money":
                if ( amount > 0){
                    log = "+" + amount + "$ to " + name;
                } else {
                    log = amount + "$ from " + name;
                }
                break;
            case "infect":
                if ( amount == 0){
                    log = name + "'s infection is ended!";
                } else {
                    log = name + " become infected!";
                }
                break;
            default:
                if ( changeType.startsWith("addCity")){
                    log = name + " bought " + changeType.replaceAll("addCity", "");
                } else if ( changeType.startsWith("removeCity")){
                    log = name + " sold " + changeType.replaceAll("removeCity", "");
                }
        }
        Text text = new Text(log);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        if ( gameLog.getChildren().size() > 5) {
            gameLog.getChildren().subList(0, gameLog.getChildren().size() - 5).clear();
        }
        gameLog.getChildren().add(text);
    }
}
