package uet.oop.bomberman;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.shape.*;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;

public class Menu extends Parent {
    private static boolean isStart = false;

    public static boolean getIsStart() {
        return isStart;
    }

    public void startGame() {
        isStart = true;
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

    public Menu(double scrW, double scrH) {
        VBox btnBox = new VBox(10);
        VBox backBox = new VBox(10);
        BorderPane settingMenu = new BorderPane();
        BorderPane mainMenu = new BorderPane();
        BorderPane stageMenu = new BorderPane();

        int offset = 200 * Sprite.SCALE;
        mainMenu.setTranslateX(0);
        mainMenu.setTranslateY(0);
        stageMenu.setTranslateX(offset);
        stageMenu.setTranslateY(0);

        btnBox.setTranslateX((scrW + MenuButton.BUTTON_WIDTH) / 2);
        btnBox.setTranslateY((scrH - MenuButton.BUTTON_HEIGHT * 2) / 2);

        backBox.setTranslateX(0);
        backBox.setTranslateY(15 * Sprite.SCALE);

        MenuButton btnStart = new MenuButton("START", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        btnStart.setOnMouseClicked(event -> {
            startGame();
        });

        MenuButton btnStages = new MenuButton("STAGES", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        btnStages.setOnMouseClicked(event -> {
            getChildren().add(stageMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
            tt.setToX(-offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), stageMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(mainMenu);
            });
        });

        MenuButton btnSetting = new MenuButton("SETTING", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        btnSetting.setOnMouseClicked(event -> {

        });

        MenuButton btnExit = new MenuButton("EXIT", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT);
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        MenuButton btnBack = new MenuButton("BACK", MenuButton.BUTTON_WIDTH / 2, MenuButton.BUTTON_HEIGHT);
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(mainMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageMenu);
            tt.setToX(offset * 2);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(stageMenu);
            });
        });

        ImageView bgView = setUpImageView("/menu/new_bg.png", 0, 0, scrW, scrH);
        ImageView titleView = setUpImageView("/menu/title.png", 20 * Sprite.SCALE, 20 * Sprite.SCALE,
                204 * Sprite.SCALE, 56 * Sprite.SCALE);
        ImageView decoView = setUpImageView("/menu/deco.png", 15 * Sprite.SCALE, 80 * Sprite.SCALE,
                123 * Sprite.SCALE, 93 * Sprite.SCALE);

        btnBox.getChildren().addAll(btnStart, btnStages, btnSetting, btnExit);
        backBox.getChildren().add(btnBack);
        stageMenu.getChildren().add(backBox);
        mainMenu.getChildren().addAll(titleView, decoView, btnBox);

        getChildren().addAll(bgView, mainMenu);
    }
}
