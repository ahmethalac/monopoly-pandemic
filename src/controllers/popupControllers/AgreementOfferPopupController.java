package controllers.popupControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Agreement;

public class AgreementOfferPopupController extends PopupController {
    @FXML
    public TextArea description;
    @FXML
    public Label agreementName;

    private Agreement agreement;

    @FXML
    void declineButtonClicked(ActionEvent event) {
        agreement.decline();
        closeStage(event);
    }

    @FXML
    void acceptButtonClicked(ActionEvent event) {
        agreement.accept();
        closeStage(event);
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
        agreementName.setText("Agreement name: " + agreement.getName());
        description.setText(agreement.toString());
    }
}
