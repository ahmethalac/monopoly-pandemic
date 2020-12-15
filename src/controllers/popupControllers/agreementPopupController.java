package controllers.popupControllers;

import models.Agreement;

public class agreementPopupController extends popupController{
    private Agreement agreement;

    public void setAgreement(Agreement agreement){
        this.agreement = agreement;
    }
}
