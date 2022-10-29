package uet.oop.bomberman.menu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URISyntaxException;

public class MenuButton extends StackPane {
    private final Text text;

    public MenuButton(String name, int btnW, int btnH, double fontSize, String fontUrl) {
        //font = new Font("Cooper Black", FONT_SIZE);
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream(fontUrl), fontSize);

        text = new Text(name);
        text.setFont(font);
        text.setFill(Color.WHITE);

        setAlignment(Pos.CENTER);

        Image btnUp = null;
        Image btnDown = null;
        try {
            btnUp = new Image(MenuButton.class.getResource("/menu/button_up.png").toURI().toString(), true);
            btnDown = new Image(MenuButton.class.getResource("/menu/button_down.png").toURI().toString(), true);
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
