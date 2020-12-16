package controllers.popupControllers;

import models.Agreement;

public class AgreementPopupController extends PopupController {
    private Agreement agreement;

    public void setAgreement(Agreement agreement){
        this.agreement = agreement;
    }
}
