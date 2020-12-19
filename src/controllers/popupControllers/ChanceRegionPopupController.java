package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChanceRegionPopupController extends PopupController{

    @FXML private Label label;

    public void setLabel(){
        label.setText(GameManager.getInstance().getCurrentChanceCard().getCardString());
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }
}
