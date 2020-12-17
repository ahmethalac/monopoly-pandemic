package controllers.popupControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import models.Agreement;
import utils.ColorUtil;
import java.net.URL;
import java.util.ResourceBundle;

public class AgreementPopupController extends PopupController implements Initializable {
    @FXML
    public ComboBox<String> choosePlayerBox;
    @FXML
    public ComboBox<String> chooseOfferBox;
    @FXML
    public ComboBox<String> chooseOppositeOfferBox;

    private Agreement agreement;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    public void setAgreement(Agreement agreement){
        this.agreement = agreement;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("aggrement popup initialized");
        choosePlayerBox.setItems(FXCollections.observableArrayList(ColorUtil.colors));
        chooseOfferBox.setItems(FXCollections.observableArrayList(ColorUtil.colors));
        chooseOppositeOfferBox.setItems(FXCollections.observableArrayList(ColorUtil.colors));
    }
}
