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
            "cyan"};
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
            default -> Color.BLACK;
        };
    }

    public static Color getLightColor(String colorString) {
        return switch (colorString) {
            case "red" -> Color.rgb(255,127,127);
            case "blue" -> Color.LIGHTBLUE;
            case "pink" -> Color.LIGHTPINK;
            case "green" -> Color.LIGHTGREEN;
            case "yellow" -> Color.LIGHTYELLOW;
            case "orange" -> Color.ORANGE;
            case "purple" -> Color.rgb(214,127,255);
            case "cyan" -> Color.LIGHTCYAN;
            default -> Color.BLACK;
        };
    }
}
