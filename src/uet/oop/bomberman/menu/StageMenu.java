package uet.oop.bomberman.menu;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.game.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;
import uet.oop.bomberman.sound.Sound;

public class StageMenu extends Menu {
    public void setUpStageMenu(double scrW, double scrH, int stage, Group root,
                               MainMenu mainMenu, Canvas canvas, LevelLoader lvLoad) {
        Font stFont = Font.loadFont(MenuButton.class.getResourceAsStream(URL_FONT3), FONT_SIZE * 1.5);
        Font scFont = Font.loadFont(MenuButton.class.getResourceAsStream(URL_FONT2), FONT_SIZE * 1.2);

        Text stText = new Text("Stage " + stage);
        int score = lvLoad.board.getScore() + BombermanGame.countdown.timeLeftValue() * 10 + BombermanGame.LIVES * 1000;
        Text scText = new Text("Score: " + score);
        stText.setFont(stFont);
        scText.setFont(scFont);
        stText.setFill(Color.LAVENDER);
        scText.setFill(Color.YELLOW);
        double textWidth = FONT_SIZE * 5.5;

        VBox textBox = new VBox(15);
        textBox.setAlignment(Pos.CENTER);
        textBox.setTranslateX((scrW - textWidth) / 2);
        textBox.setTranslateY(90 * Sprite.SCALE);
        textBox.getChildren().addAll(stText, scText);

        int btnWidth = (BUTTON_WIDTH / 3) * 2;
        MenuButton btnMenu = new MenuButton("MENU", btnWidth,
                BUTTON_HEIGHT, FONT_SIZE, URL_FONT2);
        MenuButton btnNext = new MenuButton("NEXT", btnWidth,
                BUTTON_HEIGHT, FONT_SIZE, URL_FONT2);

        btnMenu.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            returnMenu(root, mainMenu, canvas);
        });

        btnNext.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            nextLevel(mainMenu, lvLoad);
        });

        HBox btnBox = new HBox(20);
        btnBox.getChildren().addAll(btnMenu, btnNext);
        btnBox.setTranslateX((scrW - (btnWidth * 2 + 20)) / 2);
        btnBox.setTranslateY(140 * Sprite.SCALE);

        ImageView bgView = setUpImageView("/menu/win_menu_bg.png", 0, 0, scrW, scrH);
        getChildren().addAll(bgView, textBox, btnBox);
    }

    public void returnMenu(Group root, MainMenu mainMenu, Canvas canvas) {
        root.getChildren().clear();
        root.getChildren().addAll(canvas, mainMenu);
    }

    public void nextLevel(MainMenu mainMenu, LevelLoader lvLoad) {
        mainMenu.setStartGame(true);
        BombermanGame.canvasAdded = true;
        BombermanGame.LEVEL++;
        BombermanGame.LIVES = 2;
        lvLoad.loadLevel(BombermanGame.LEVEL);
        lvLoad.introLevel.setShowIntro(true);
        lvLoad.introLevel.resetTime();
    }
}
