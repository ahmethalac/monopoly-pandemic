package controllers.popupControllers;

import controllers.modelcontrollers.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class PirateRegionPopupController extends PopupController implements Initializable {

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
