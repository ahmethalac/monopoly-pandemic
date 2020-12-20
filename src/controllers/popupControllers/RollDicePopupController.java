package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class RollDicePopupController extends PopupController{
    @FXML private ImageView imageView1;
    private Image image1;
    private Image image2;
    @FXML private ImageView imageView2;

    public void setDice(int die1, int die2){
        for(int i = 1; i <= 6; i++)
        {
            if(die1 == i){
                image1 = new Image(getClass().getResourceAsStream("../../assets/icons/dice" + i + ".png"));
            }
            if(die2 == i){
                image2 = new Image(getClass().getResourceAsStream("../../assets/icons/dice" + i  + ".png"));
            }
        }

        imageView1.setImage(image1);
        imageView2.setImage(image2);
    }
    @FXML void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
