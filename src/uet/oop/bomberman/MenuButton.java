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
    public static int FONT_SIZE = 15 * Sprite.SCALE;
    public static int BUTTON_WIDTH = 70 * Sprite.SCALE;
    public static int BUTTON_HEIGHT = 18 * Sprite.SCALE;


    public MenuButton(String name) {
        //font = new Font("Cooper Black", FONT_SIZE);
        font = Font.loadFont(MenuButton.class.getResourceAsStream("/font/Font2.ttf"), FONT_SIZE);

        text = new Text(name);
        text.setFont(font);
        text.setFill(Color.BLACK);

        setAlignment(Pos.CENTER_LEFT);
        //setRotate(0);

        getChildren().add(text);

        setOnMouseEntered(event -> {
            text.setTranslateX(10);
            text.setFill(Color.rgb(227, 110, 45));
        });

        setOnMouseExited(event -> {
            text.setTranslateX(0);
            text.setFill(Color.BLACK);
        });

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
}
