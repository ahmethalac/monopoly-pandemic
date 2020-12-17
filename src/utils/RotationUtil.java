package utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class RotationUtil {
    public static void rotateAroundPoint(double pivotX, double pivotY, Node node, double angle){
        double x = node.getTranslateX();
        double y = node.getTranslateY();

        //translate back to origin
        x -= pivotX;
        y -= pivotY;

        //rotate
        double newX = x * Math.cos(angle * Math.PI / 180) - y * Math.sin(angle * Math.PI / 180);
        double newY = x * Math.sin(angle * Math.PI / 180) + y * Math.cos(angle * Math.PI / 180);

        node.setTranslateX(newX + pivotX);
        node.setTranslateY(newY + pivotY);
    }

    public static void rotateAroundCenter(Node node, double angle){
        double x = node.getTranslateX();
        double y = node.getTranslateY();
        double rotate = node.getRotate();
        System.out.println(x);
        System.out.println(y);
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.translateYProperty(), x * Math.sin(angle * Math.PI / 180) + y * Math.cos(angle * Math.PI / 180)))
        );
        timeline2.play();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.translateXProperty(), x * Math.cos(angle * Math.PI / 180) - y * Math.sin(angle * Math.PI / 180)))
        );
        timeline.play();

        Timeline timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(angle / 60), new KeyValue(node.rotateProperty(), rotate + angle))
        );
        timeline3.play();
    }
}
