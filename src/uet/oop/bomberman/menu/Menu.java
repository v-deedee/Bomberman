package uet.oop.bomberman.menu;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;
import uet.oop.bomberman.score.Score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URISyntaxException;

public class Menu extends Parent {
    private int currentStage = 1;
    private boolean startGame = false;
    public static boolean[] stageLocked = new boolean[10];
    public static final int STAGE_BTN_W = MenuButton.BUTTON_WIDTH / 2;
    public static final int STAGE_BTN_H = MenuButton.BUTTON_HEIGHT * 2;

    public Menu() {
        for (int i = 2; i < 10; i++) {
            stageLocked[i] = true;
        }
        readStageStatus();
    }

    void readStageStatus() {
        try {
            FileReader fr = new FileReader("res\\levels\\StageStatus.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 1; i <= 9; i++) {
                String line = br.readLine();
                int t = Integer.parseInt(line);
                stageLocked[i] = t == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeStageStatus() {
        try {
            FileWriter fr = new FileWriter("res\\levels\\StageStatus.txt");
            BufferedWriter bw = new BufferedWriter(fr);
            for (int i = 1; i <= 9; i++) {
                String t = "0";
                if (stageLocked[i]) t = "1";
                bw.write(t);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unlockStage(int index) {
        stageLocked[index] = false;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public int getCurrentStage() {
        return currentStage;
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

    public BorderPane setUpStageMenu(BorderPane mainMenu, int offset, LevelLoader lvLoad) {
        BorderPane stageMenu = new BorderPane();
        stageMenu.setTranslateX(offset);
        stageMenu.setTranslateY(0);

        ImageView[] lock = new ImageView[10];
        for (int i = 2; i < 10; i++) {
            lock[i] = setUpImageView("/menu/lock.png", 0, 0, STAGE_BTN_W, STAGE_BTN_H);
        }

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
                    col1.getChildren().add(stageBtn[i]);
                }
            } else if (i % 3 == 2) {
                if (stageLocked[i]) {
                    col2.getChildren().add(lock[i]);
                } else {
                    col2.getChildren().add(stageBtn[i]);
                }
            } else {
                if (stageLocked[i]) {
                    col3.getChildren().add(lock[i]);
                } else {
                    col3.getChildren().add(stageBtn[i]);
                }
            }
        }
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            stageBtn[i].setOnMouseClicked(event -> {
                startGame = true;
                lvLoad.loadLevel(finalI);
                currentStage = finalI;
            });
        }

        MenuButton btnBack = new MenuButton("BACK", MenuButton.BUTTON_WIDTH / 2, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(mainMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), stageMenu);
            tt.setToX(offset * 2);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(stageMenu));
        });
        VBox backBox = new VBox(10);
        backBox.setTranslateX(0);
        backBox.setTranslateY(15 * Sprite.SCALE);
        backBox.getChildren().add(btnBack);

        ImageView stageTitleView = setUpImageView("/menu/stage_title.png", 75 * Sprite.SCALE, 30 * Sprite.SCALE,
                108 * Sprite.SCALE, 21 * Sprite.SCALE);
        ImageView deco2View = setUpImageView("/menu/deco2.png", 10 * Sprite.SCALE, 70 * Sprite.SCALE,
                68 * Sprite.SCALE, 76 * Sprite.SCALE);

        stageMenu.getChildren().addAll(backBox, stageTitleView, deco2View, col1, col2, col3);

        return stageMenu;
    }

    public BorderPane setUpSettingMenu(BorderPane mainMenu, int offset) {
        BorderPane settingMenu = new BorderPane();
        settingMenu.setTranslateX(offset);
        settingMenu.setTranslateY(0);

        MenuButton btnBack = new MenuButton("BACK", MenuButton.BUTTON_WIDTH / 2, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(mainMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), settingMenu);
            tt.setToX(offset * 2);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(settingMenu));
        });

        VBox backBox = new VBox();
        backBox.setTranslateX(0);
        backBox.setTranslateY(15 * Sprite.SCALE);
        backBox.getChildren().add(btnBack);

        HBox musicBox = new HBox(56 * Sprite.SCALE);
        HBox soundBox = new HBox(25 * Sprite.SCALE);
        musicBox.setTranslateX(50 * Sprite.SCALE);
        musicBox.setTranslateY(50 * Sprite.SCALE);
        soundBox.setTranslateX(50 * Sprite.SCALE);
        soundBox.setTranslateY((100 * Sprite.SCALE));
        Font font = Font.loadFont(Menu.class.getResourceAsStream(MenuButton.URL_FONT2), MenuButton.FONT_SIZE * 2);
        Text mText = new Text("MUSIC");
        mText.setFont(font);
        mText.setFill(Color.BEIGE);
        Text sText = new Text("SOUNDFX");
        sText.setFont(font);
        sText.setFill(Color.BEIGE);
        ImageView btnOn1 = setUpImageView("/menu/button_on.png", 0, 0, 35 * Sprite.SCALE, 15 * Sprite.SCALE);
        ImageView btnOn2 = setUpImageView("/menu/button_on.png", 0, 0, 35 * Sprite.SCALE, 15 * Sprite.SCALE);
        ImageView btnOff1 = setUpImageView("/menu/button_off.png", 0, 0, 35 * Sprite.SCALE, 15 * Sprite.SCALE);
        ImageView btnOff2 = setUpImageView("/menu/button_off.png", 0, 0, 35 * Sprite.SCALE, 15 * Sprite.SCALE);
        musicBox.getChildren().addAll(mText, btnOn1);
        soundBox.getChildren().addAll(sText, btnOn2);
        settingMenu.getChildren().addAll(backBox, musicBox, soundBox);

        btnOn1.setOnMouseClicked(event -> {
            musicBox.getChildren().remove(btnOn1);
            musicBox.getChildren().add(btnOff1);
            Board.BGMusic = false;
        });
        btnOff1.setOnMouseClicked(event -> {
            musicBox.getChildren().remove(btnOff1);
            musicBox.getChildren().add(btnOn1);
            Board.BGMusic = true;
        });
        btnOn2.setOnMouseClicked(event -> {
            soundBox.getChildren().remove(btnOn2);
            soundBox.getChildren().add(btnOff2);
            Board.soundFX = false;
        });
        btnOff2.setOnMouseClicked(event -> {
            soundBox.getChildren().remove(btnOff2);
            soundBox.getChildren().add(btnOn2);
            Board.soundFX = true;
        });

        return settingMenu;
    }

    public void setUpMainMenu(double scrW, double scrH, LevelLoader lvLoad) {
        int offset = 200 * Sprite.SCALE;

        BorderPane mainMenu = new BorderPane();
        BorderPane stageMenu = setUpStageMenu(mainMenu, offset, lvLoad);
        BorderPane settingMenu = setUpSettingMenu(mainMenu, offset);

        MenuButton btnStart = new MenuButton("NEW GAME", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnStart.setOnMouseClicked(event -> {
            startGame = true;
            lvLoad.loadLevel(1);
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

            tt.setOnFinished(event1 -> getChildren().remove(mainMenu));
        });

        MenuButton btnSetting = new MenuButton("SETTING", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnSetting.setOnMouseClicked(event -> {
            getChildren().add(settingMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
            tt.setToX(-offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), settingMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(mainMenu));
        });

        MenuButton btnExit = new MenuButton("EXIT", MenuButton.BUTTON_WIDTH, MenuButton.BUTTON_HEIGHT,
                MenuButton.FONT_SIZE, MenuButton.URL_FONT2);
        btnExit.setOnMouseClicked(event -> {
            Menu.writeStageStatus();
            Score.writeHighScore();
            System.exit(0);
        });

        ImageView bgView = setUpImageView("/menu/new_bg.png", 0, 0, scrW, scrH);
        ImageView titleView = setUpImageView("/menu/title.png", 20 * Sprite.SCALE, 20 * Sprite.SCALE,
                204 * Sprite.SCALE, 56 * Sprite.SCALE);
        ImageView decoView = setUpImageView("/menu/deco.png", 15 * Sprite.SCALE, 80 * Sprite.SCALE,
                123 * Sprite.SCALE, 93 * Sprite.SCALE);

        VBox btnBox = new VBox(10);
        btnBox.setTranslateX((scrW + MenuButton.BUTTON_WIDTH) / 2);
        btnBox.setTranslateY((scrH - MenuButton.BUTTON_HEIGHT * 2) / 2);
        btnBox.getChildren().addAll(btnStart, btnStages, btnSetting, btnExit);

        mainMenu.getChildren().addAll(titleView, decoView, btnBox);

        getChildren().addAll(bgView, mainMenu);
    }
}
