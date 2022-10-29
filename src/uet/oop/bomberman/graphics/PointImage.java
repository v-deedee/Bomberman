package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;

import java.net.URISyntaxException;

public class PointImage {
    public static Image point100;
    public static Image point200;
    public static Image point300;
    public static Image point400;
    public static Image point500;

    static {
        try {
            point100 = new Image(PointImage.class.getResource("/sprites/100_point.png").toURI().toString(), true);
            point200 = new Image(PointImage.class.getResource("/sprites/200_point.png").toURI().toString(), true);
            point300 = new Image(PointImage.class.getResource("/sprites/300_point.png").toURI().toString(), true);
            point400 = new Image(PointImage.class.getResource("/sprites/400_point.png").toURI().toString(), true);
            point500 = new Image(PointImage.class.getResource("/sprites/500_point.png").toURI().toString(), true);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
