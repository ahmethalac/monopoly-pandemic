package utils;

import javafx.scene.paint.Color;

public class colorUtil {

    public static final String[] colors = new String[]{
            "red",
            "blue",
            "pink",
            "green",
            "yellow",
            "orange",
            "purple",
            "cyan",
            "grey",
            "brown"};
    public static Color getFXColor(String colorString) {

        return switch (colorString) {
            case "red" -> Color.RED;
            case "blue" -> Color.BLUE;
            case "pink" -> Color.PINK;
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            case "orange" -> Color.ORANGE;
            case "purple" -> Color.PURPLE;
            case "cyan" -> Color.CYAN;
            case "grey" -> Color.GREY;
            case "brown" -> Color.BROWN;
            default -> Color.BLACK;
        };
    }
}
