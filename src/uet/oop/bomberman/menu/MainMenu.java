package uet.oop.bomberman.menu;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;
import uet.oop.bomberman.score.Score;
import uet.oop.bomberman.sound.Sound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MainMenu extends Menu {
    private boolean startGame = false;
    private static final boolean[] stageLocked = new boolean[10];
    public static final int STAGE_BTN_W = BUTTON_WIDTH / 2;
    public static final int STAGE_BTN_H = BUTTON_HEIGHT * 2;

    public MainMenu() {
        readStageStatus();
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public void unlockStage(int index) {
        stageLocked[index] = false;
    }

    public static void readStageStatus() {
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
        stageBtn[1] = new MenuButton("1", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[4] = new MenuButton("4", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[7] = new MenuButton("7", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        col1.setTranslateX(100 * Sprite.SCALE);
        col1.setTranslateY((60 + 16) * Sprite.SCALE);

        VBox col2 = new VBox(10 * Sprite.SCALE);
        stageBtn[2] = new MenuButton("2", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[5] = new MenuButton("5", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[8] = new MenuButton("8", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        col2.setTranslateX(150 * Sprite.SCALE);
        col2.setTranslateY((60 + 16) * Sprite.SCALE);

        VBox col3 = new VBox(10 * Sprite.SCALE);
        stageBtn[3] = new MenuButton("3", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[6] = new MenuButton("6", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        stageBtn[9] = new MenuButton("9", STAGE_BTN_W, STAGE_BTN_H, FONT_SIZE * 1.5, URL_FONT3);
        col3.setTranslateX(200 * Sprite.SCALE);
        col3.setTranslateY((60 + 16) * Sprite.SCALE);

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
                if (Board.soundFX) {
                    Sound.buttonClickAudio.play();
                }
                startGame = true;
                BombermanGame.canvasAdded = true;
                BombermanGame.LEVEL = finalI;
                BombermanGame.LIVES = 2;
                lvLoad.loadLevel(finalI);
                lvLoad.introLevel.setShowIntro(true);
                lvLoad.introLevel.resetTime();
            });
        }

        MenuButton btnBack = new MenuButton("BACK", BUTTON_WIDTH / 2, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnBack.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
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

        ImageView stageTitleView = setUpImageView("/menu/stage_title.png", 75 * Sprite.SCALE, (30 + 8) * Sprite.SCALE,
                108 * Sprite.SCALE, 21 * Sprite.SCALE);
        ImageView deco2View = setUpImageView("/menu/deco2.png", 10 * Sprite.SCALE, (70 + 16) * Sprite.SCALE,
                68 * Sprite.SCALE, 76 * Sprite.SCALE);

        stageMenu.getChildren().addAll(backBox, stageTitleView, deco2View, col1, col2, col3);

        return stageMenu;
    }

    public BorderPane setUpSettingMenu(BorderPane mainMenu, int offset) {
        BorderPane settingMenu = new BorderPane();
        settingMenu.setTranslateX(offset);
        settingMenu.setTranslateY(0);

        MenuButton btnBack = new MenuButton("BACK", BUTTON_WIDTH / 2, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnBack.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
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
        musicBox.setTranslateY((50 + 32) * Sprite.SCALE);
        soundBox.setTranslateX(50 * Sprite.SCALE);
        soundBox.setTranslateY(((100 + 32) * Sprite.SCALE));
        Font font = Font.loadFont(MainMenu.class.getResourceAsStream(URL_FONT2), FONT_SIZE * 2);
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

        if (Board.BGMusic) {
            musicBox.getChildren().addAll(mText, btnOn1);
        } else {
            musicBox.getChildren().addAll(mText, btnOff1);
        }
        if (Board.soundFX) {
            soundBox.getChildren().addAll(sText, btnOn2);
        } else {
            soundBox.getChildren().addAll(sText, btnOff2);
        }

        settingMenu.getChildren().addAll(backBox, musicBox, soundBox);

        btnOn1.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            musicBox.getChildren().remove(btnOn1);
            musicBox.getChildren().add(btnOff1);
            Board.BGMusic = false;
        });
        btnOff1.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            musicBox.getChildren().remove(btnOff1);
            musicBox.getChildren().add(btnOn1);
            Board.BGMusic = true;
        });
        btnOn2.setOnMouseClicked(event -> {
            Board.soundFX = false;
            soundBox.getChildren().remove(btnOn2);
            soundBox.getChildren().add(btnOff2);
        });
        btnOff2.setOnMouseClicked(event -> {
            Sound.buttonClickAudio.play();
            Board.soundFX = true;
            soundBox.getChildren().remove(btnOff2);
            soundBox.getChildren().add(btnOn2);
        });

        return settingMenu;
    }

    public BorderPane setUpHighScoreMenu(BorderPane mainMenu, int offset, double scrW, double scrH) {
        BorderPane highScoreMenu = new BorderPane();
        highScoreMenu.setTranslateX(offset);
        highScoreMenu.setTranslateY(0);

        MenuButton btnBack = new MenuButton("BACK", BUTTON_WIDTH / 2, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnBack.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            getChildren().add(mainMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), highScoreMenu);
            tt.setToX(offset * 2);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(highScoreMenu));
        });

        VBox backBox = new VBox();
        backBox.setTranslateX(0);
        backBox.setTranslateY(15 * Sprite.SCALE);
        backBox.getChildren().add(btnBack);

        double tableW = 128 * Sprite.SCALE;
        double tableH = 116 * Sprite.SCALE;
        ImageView scoreTable = setUpImageView("/menu/score_table.png", (scrW - tableW) / 2, (scrH - tableH) / 2, tableW, tableH);

        VBox score = new VBox(1.5 * Sprite.SCALE);
        score.setAlignment(Pos.CENTER);
        score.setTranslateX(scrW / 2 + 32 * Sprite.SCALE);
        score.setTranslateY(scrH / 2 + 5 * Sprite.SCALE);
        Font font = new Font("Cooper Black", FONT_SIZE / 1.2);
        Text[] scoreText = new Text[9];
        for (int i = 0; i < 9; i++) {
            scoreText[i] = new Text(Score.highScore.get(i).toString());
            scoreText[i].setFont(font);
            scoreText[i].setFill(Color.BLACK);
            score.getChildren().add(scoreText[i]);
        }

        highScoreMenu.getChildren().addAll(backBox, scoreTable, score);

        return highScoreMenu;
    }

    public void setUpMainMenu(double scrW, double scrH, LevelLoader lvLoad) {
        int offset = 200 * Sprite.SCALE;

        BorderPane mainMenu = new BorderPane();
        BorderPane stageMenu = setUpStageMenu(mainMenu, offset, lvLoad);
        BorderPane settingMenu = setUpSettingMenu(mainMenu, offset);
        BorderPane highScoreMenu = setUpHighScoreMenu(mainMenu, offset, scrW, scrH);

        MenuButton btnStart = new MenuButton("NEW GAME", BUTTON_WIDTH, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnStart.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            startGame = true;
            BombermanGame.canvasAdded = true;
            BombermanGame.LEVEL = 1;
            BombermanGame.LIVES = 2;
            lvLoad.loadLevel(1);
            lvLoad.introLevel.setShowIntro(true);
            lvLoad.introLevel.resetTime();
        });

        MenuButton btnStages = new MenuButton("STAGES", BUTTON_WIDTH, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnStages.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            getChildren().add(stageMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
            tt.setToX(-offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), stageMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(mainMenu));
        });

        MenuButton btnSetting = new MenuButton("SETTING", BUTTON_WIDTH, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnSetting.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            getChildren().add(settingMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
            tt.setToX(-offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), settingMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(mainMenu));
        });

        MenuButton btnHighScore = new MenuButton("HIGH SCORE", BUTTON_WIDTH, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnHighScore.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            getChildren().add(highScoreMenu);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
            tt.setToX(-offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), highScoreMenu);
            tt1.setToX(0);

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> getChildren().remove(mainMenu));
        });

        MenuButton btnExit = new MenuButton("EXIT", BUTTON_WIDTH, BUTTON_HEIGHT,
                FONT_SIZE, URL_FONT2);
        btnExit.setOnMouseClicked(event -> {
            MainMenu.writeStageStatus();
            Score.writeHighScore();
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            System.exit(0);
        });

        ImageView bgView = setUpImageView("/menu/new_bg.png", 0, 0, scrW, scrH);
        ImageView titleView = setUpImageView("/menu/title.png", 20 * Sprite.SCALE, (20 + 8) * Sprite.SCALE,
                204 * Sprite.SCALE, 56 * Sprite.SCALE);
        ImageView decoView = setUpImageView("/menu/deco.png", 15 * Sprite.SCALE, (80 + 16) * Sprite.SCALE,
                123 * Sprite.SCALE, 93 * Sprite.SCALE);

        VBox btnBox = new VBox(10);
        btnBox.setTranslateX((scrW + BUTTON_WIDTH) / 2);
        btnBox.setTranslateY((scrH - BUTTON_HEIGHT * 2.5) / 2);
        btnBox.getChildren().addAll(btnStart, btnStages, btnSetting, btnHighScore, btnExit);

        mainMenu.getChildren().addAll(titleView, decoView, btnBox);

        getChildren().addAll(bgView, mainMenu);
    }
}
