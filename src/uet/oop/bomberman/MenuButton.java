package uet.oop.bomberman;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;

public class MenuButton extends StackPane {
    private Text text;
    private Font font;

    public static int FONT_SIZE = 10 * Sprite.SCALE;
    public static int BUTTON_WIDTH = 70 * Sprite.SCALE;
    public static int BUTTON_HEIGHT = 18 * Sprite.SCALE;
    public static String URL_FONT2 = "/font/Font2.ttf";
    public static String URL_FONT3 = "/font/Font3.ttf";

    public MenuButton(String name, int btnW, int btnH, double fontSize, String fontUrl) {
        //font = new Font("Cooper Black", FONT_SIZE);
        font = Font.loadFont(MenuButton.class.getResourceAsStream(fontUrl), fontSize);

        text = new Text(name);
        text.setFont(font);
        text.setFill(Color.WHITE);

        setAlignment(Pos.CENTER);
        //setRotate(0);

        Rectangle bg = new Rectangle(btnW, btnH);
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(5));

        Image btnUp = null;
        Image btnDown = null;
        try {
            btnUp = new Image(Menu.class.getResource("/menu/button_up.png").toURI().toString(), true);
            btnDown = new Image(Menu.class.getResource("/menu/button_down.png").toURI().toString(), true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImageView btnUpView = new ImageView(btnUp);
        btnUpView.setFitHeight(btnH);
        btnUpView.setFitWidth(btnW);

        ImageView btnDownView = new ImageView(btnDown);
        btnDownView.setFitHeight(btnH);
        btnDownView.setFitWidth(btnW);

        getChildren().addAll(btnUpView, text);

        setOnMouseEntered(event -> {
            getChildren().remove(btnUpView);
            getChildren().remove(text);
            text.setFill(Color.BLACK);
            getChildren().addAll(btnDownView, text);
        });

        setOnMouseExited(event -> {
            getChildren().remove(btnDownView);
            getChildren().remove(text);
            text.setFill(Color.WHITE);
            getChildren().addAll(btnUpView, text);
        });

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
}
