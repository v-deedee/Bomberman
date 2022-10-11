package uet.oop.bomberman;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
    private Text text;

    public MenuButton(String name) {
        text = new Text(name);
        text.setFont(text.getFont().font(30));
        text.setFill(Color.WHITE);

        Rectangle bg = new Rectangle(150, 50);
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
