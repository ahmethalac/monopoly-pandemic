package utils;

import javafx.scene.image.Image;

public class Texture {
    public static Image RED = new Image(Texture.class.getResourceAsStream("../assets/textures/red.png"));
    public static Image BLUE = new Image(Texture.class.getResourceAsStream("../assets/textures/blue.png"));
    public static Image CYAN = new Image(Texture.class.getResourceAsStream("../assets/textures/cyan.png"));
    public static Image GREEN = new Image(Texture.class.getResourceAsStream("../assets/textures/green.png"));
    public static Image ORANGE = new Image(Texture.class.getResourceAsStream("../assets/textures/orange.png"));
    public static Image PINK = new Image(Texture.class.getResourceAsStream("../assets/textures/pink.png"));
    public static Image PURPLE = new Image(Texture.class.getResourceAsStream("../assets/textures/purple.png"));
    public static Image YELLOW = new Image(Texture.class.getResourceAsStream("../assets/textures/yellow.png"));

    public static Image getTexture(String color){
        return new Image(Texture.class.getResourceAsStream("../assets/textures/" + color + ".png"));
    }
}
