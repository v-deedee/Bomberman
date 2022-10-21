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
    private static boolean[] stStart = new boolean[10];
    private boolean[] stageLocked = new boolean[10];
    public static final int STAGE_BTN_W = MenuButton.BUTTON_WIDTH / 2;
    public static final int STAGE_BTN_H = MenuButton.BUTTON_HEIGHT * 2;

    public static boolean getIsStart(int level) {
        return stStart[level];
    }

    public void startGame(int level) {
        stStart[level] = true;
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

    public BorderPane setUpStageMenu(int offset, MenuButton btnBack) {
        ImageView[] lock = new ImageView[10];
        for (int i = 2; i < 10; i++) {
            stageLocked[i] = true;
            lock[i] = setUpImageView("/menu/lock.png", 0, 0, STAGE_BTN_W, STAGE_BTN_H);
        }

        VBox backBox = new VBox(10);
        BorderPane stageMenu = new BorderPane();
        stageMenu.setTranslateX(offset);
        stageMenu.setTranslateY(0);
        backBox.setTranslateX(0);
        backBox.setTranslateY(15 * Sprite.SCALE);

        MenuButton[] stageBtn = new MenuButton[10];
        VBox col1 = new VBox(10 * Sprite.SCALE);
        stageBtn[1] = new MenuButton("1", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[4] = new MenuButton("4", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[7] = new MenuButton("7", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        col1.setTranslateX(100 * Sprite.SCALE);
        col1.setTranslateY(60 * Sprite.SCALE);

        VBox col2 = new VBox(10 * Sprite.SCALE);
        stageBtn[2] = new MenuButton("2", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[5] = new MenuButton("5", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[8] = new MenuButton("8", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        col2.setTranslateX(150 * Sprite.SCALE);
        col2.setTranslateY(60 * Sprite.SCALE);

        VBox col3 = new VBox(10 * Sprite.SCALE);
        stageBtn[3] = new MenuButton("3", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[6] = new MenuButton("6", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        stageBtn[9] = new MenuButton("9", STAGE_BTN_W, STAGE_BTN_H, MenuButton.FONT_SIZE * 1.5, MenuButton.URL_FONT3);
        col3.setTranslateX(200 * Sprite.SCALE);
        col3.setTranslateY(60 * Sprite.SCALE);

        col1.getChildren().add(stageBtn[1]);
        for (int i = 2; i < 10; i++) {
            if (i % 3 == 1) {
                if (stageLocked[i]) {
                    col1.getChildren().add(lock[i]);
                } else {
                    col1.getChildren().add(stageBtn[1]);
                }
            } else if (i % 3 == 2) {
                if (stageLocked[i]) {
                    col2.getChildren().add(lock[i]);
                } else {
                    col2.getChildren().add(stageBtn[1]);
                }
            } else {
                if (stageLocked[i]) {
                    col3.getChildren().add(lock[i]);
                } else {
                    col3.getChildren().add(stageBtn[1]);
                }
            }
        }
        stageBtn[1].setOnMouseClicked(event -> {
            startGame(1);
        });

        ImageView stageTitleView = setUpImageView("/menu/stage_title.png", 75 * Sprite.SCALE, 30 * Sprite.SCALE,
                108 * Sprite.SCALE, 21 * Sprite.SCALE);
        ImageView deco2View = setUpImageView("/menu/deco2.png", 10 * Sprite.SCALE, 70 * Sprite.SCALE,
                68 * Sprite.SCALE, 76 * Sprite.SCALE);

        backBox.getChildren().add(btnBack);
        stageMenu.getChildren().addAll(backBox, stageTitleView, deco2View, col1, col2, col3);

        return stageMenu;
    }

    public Menu(double scrW, double scrH) {
        int offset = 200 * Sprite.SCALE;

        BorderPane settingMenu = new BorderPane();
        BorderPane mainMenu = new BorderPane();
        VBox btnBox = new VBox(10);

        MenuButton btnBack = new MenuButton("BACK", MenuButton.BUTTON_WIDTH / 2, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);

        BorderPane stageMenu = setUpStageMenu(offset, btnBack);

        mainMenu.setTranslateX(0);
        mainMenu.setTranslateY(0);

        btnBox.setTranslateX((scrW + MenuButton.BUTTON_WIDTH) / 2);
        btnBox.setTranslateY((scrH - MenuButton.BUTTON_HEIGHT * 2) / 2);

        MenuButton btnStart = new MenuButton("START", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnStart.setOnMouseClicked(event -> {
            startGame(1);
        });

        MenuButton btnStages = new MenuButton("STAGES", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
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

        MenuButton btnSetting = new MenuButton("SETTING", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnSetting.setOnMouseClicked(event -> {

        });

        MenuButton btnExit = new MenuButton("EXIT", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });


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

        mainMenu.getChildren().addAll(titleView, decoView, btnBox);

        getChildren().addAll(bgView, mainMenu);
    }
}
