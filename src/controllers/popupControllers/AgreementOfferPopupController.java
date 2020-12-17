package controllers.popupControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Agreement;

public class AgreementOfferPopupController extends PopupController{
    private Agreement agreement;

    @FXML
    void closeButtonClicked(ActionEvent event) {
        closeStage(event);
    }

    public void setAgreement(Agreement agreement){
        this.agreement = agreement;
    }
}
