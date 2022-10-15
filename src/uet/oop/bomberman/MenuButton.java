package uet.oop.bomberman;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

public class MenuButton extends StackPane {
    private Text text;
    private Font font;
    public static int FONT_SIZE = 10 * Sprite.SCALE;
    public static int BUTTON_WIDTH = 50 * Sprite.SCALE;
    public static int BUTTON_HEIGHT = 15 * Sprite.SCALE;


    public MenuButton(String name) {
        font = Font.getDefault().font(FONT_SIZE);

        text = new Text(name);
        text.setFont(font);
        text.setFill(Color.WHITE);

        Rectangle bg = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT);
        bg.setOpacity(0.8);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(5));

        bg.setTranslateX(0);
        text.setTranslateX(10);

        setAlignment(Pos.CENTER_LEFT);
        //setRotate(0);

        getChildren().addAll(bg, text);

        setOnMouseEntered(event -> {
            bg.setTranslateX(10);
            text.setTranslateX(20);
            bg.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        setOnMouseExited(event -> {
            bg.setTranslateX(0);
            text.setTranslateX(10);
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
}
