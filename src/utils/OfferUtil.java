package utils;

import models.*;

public class OfferUtil {
    public static final String[] offers = new String[]{
            "Sell Region",
            "Give Money",
            "Pay Rent or Not",
            "Take Percentage"};

    public static Class<?> getOfferClass(String offer) {
        return switch (offer) {
            case "Sell Region" -> SellRegion.class;
            case "Give Money" -> GiveMoney.class;
            case "Pay Rent or Not" -> PayRentOrNot.class;
            case "Take Percentage" -> TakePercentage.class;
            default -> Offer.class;
        };
    }
}
