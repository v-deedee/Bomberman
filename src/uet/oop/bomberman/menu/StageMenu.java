package uet.oop.bomberman.menu;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;

public class StageMenu extends Parent {
    public StageMenu() {
    }

    public void setUpStageMenu(double scrW, double scrH, int stage, Group root, Menu menu, Canvas canvas) {
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream(MenuButton.URL_FONT3), MenuButton.FONT_SIZE * 1.2);

        Text text = new Text("Stage " + stage);
        text.setFont(font);
        text.setFill(Color.YELLOW);
        double textWidth = MenuButton.FONT_SIZE * 4;

        VBox textBox = new VBox();
        textBox.setTranslateX((scrW - textWidth) / 2);
        textBox.setTranslateY(80 * Sprite.SCALE);
        textBox.getChildren().add(text);

        int btnWidth = (MenuButton.BUTTON_WIDTH / 3) * 2;
        MenuButton btnMenu = new MenuButton("MENU", btnWidth,
                MenuButton.BUTTON_HEIGHT, MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        MenuButton btnNext = new MenuButton("NEXT", btnWidth,
                MenuButton.BUTTON_HEIGHT, MenuButton.FONT_SIZE, MenuButton.URL_FONT2);

        btnMenu.setOnMouseClicked(event -> returnMenu(root, menu, canvas));

        btnNext.setOnMouseClicked(event -> nextLevel(root, menu, canvas));

        HBox btnBox = new HBox(20);
        btnBox.getChildren().addAll(btnMenu, btnNext);
        btnBox.setTranslateX((scrW - (btnWidth * 2 + 20)) / 2);
        btnBox.setTranslateY(110 * Sprite.SCALE);

        ImageView bgView = setUpImageView("/menu/win_menu_bg.png", 0, 0, scrW, scrH);
        getChildren().addAll(bgView, textBox, btnBox);
    }

    public ImageView setUpImageView(String url, double x, double y, double w, double h) {
        Image img = null;
        try {
            img = new Image(Menu.class.getResource(url).toURI().toString(), true);
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

    public void returnMenu(Group root, Menu menu, Canvas canvas) {
        root.getChildren().clear();
        root.getChildren().addAll(canvas, menu);
    }

    public void nextLevel(Group root, Menu menu, Canvas canvas) {
        menu.setStartGame(true);
        root.getChildren().clear();
        root.getChildren().add(canvas);
    }
}
