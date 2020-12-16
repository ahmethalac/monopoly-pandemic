package utils;

import javafx.scene.paint.Color;

public class ColorUtil {

    public static final String[] colors = new String[]{
            "Red",
            "Blue",
            "Pink",
            "Green",
            "Yellow",
            "Orange",
            "Purple",
            "Cyan"};
    public static Color getFXColor(String colorString) {

        return switch (colorString) {
            case "Red" -> Color.RED;
            case "Blue" -> Color.BLUE;
            case "Pink" -> Color.PINK;
            case "Green" -> Color.GREEN;
            case "Yellow" -> Color.YELLOW;
            case "Orange" -> Color.ORANGE;
            case "Purple" -> Color.PURPLE;
            case "Cyan" -> Color.CYAN;
            default -> Color.BLACK;
        };
    }

    public static Color getLightColor(String colorString) {
        return switch (colorString) {
            case "Red" -> Color.rgb(255,127,127);
            case "Blue" -> Color.LIGHTBLUE;
            case "Pink" -> Color.LIGHTPINK;
            case "Green" -> Color.LIGHTGREEN;
            case "Yellow" -> Color.LIGHTYELLOW;
            case "Orange" -> Color.ORANGE;
            case "Purple" -> Color.rgb(214,127,255);
            case "Cyan" -> Color.LIGHTCYAN;
            default -> Color.BLACK;
        };
    }
}
