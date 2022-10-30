package uet.oop.bomberman.menu;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;

public class Menu extends Parent {
    public static int FONT_SIZE = 10 * Sprite.SCALE;
    public static String URL_FONT1 = "/font/Font1.ttf";
    public static String URL_FONT2 = "/font/Font2.ttf";
    public static String URL_FONT3 = "/font/Font3.ttf";
    public static int BUTTON_WIDTH = 70 * Sprite.SCALE;
    public static int BUTTON_HEIGHT = 18 * Sprite.SCALE;

    public ImageView setUpImageView(String url, double x, double y, double w, double h) {
        Image img = null;
        try {
            img = new Image(MainMenu.class.getResource(url).toURI().toString(), true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(h);
        imgView.setFitWidth(w);
        imgView.setX(x);
        imgView.setY(y);

        return imgView;
    }
}
