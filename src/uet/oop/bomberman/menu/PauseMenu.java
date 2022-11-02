package uet.oop.bomberman.menu;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.objects.Board;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;
import uet.oop.bomberman.sound.Sound;

public class PauseMenu extends Menu {
    public void setUpPauseMenu(double scrW, double scrH, Group root, LevelLoader lvLoad) {
        Rectangle bg = new Rectangle(0, 0, (int) scrW, (int) scrH);
        bg.setOpacity(0.5);
        bg.setFill(Color.BLACK);

        int menuW = 125 * Sprite.SCALE;
        int menuH = 125 * Sprite.SCALE;
        ImageView menuView = setUpImageView("/menu/pause_menu.png", (scrW - menuW) / 2, (scrH - menuH) / 2, menuW, menuH);

        HBox musicBox = new HBox(32 * Sprite.SCALE);
        HBox soundBox = new HBox(15 * Sprite.SCALE);
        musicBox.setTranslateX(80 * Sprite.SCALE);
        musicBox.setTranslateY((60 + 32) * Sprite.SCALE);
        soundBox.setTranslateX(80 * Sprite.SCALE);
        soundBox.setTranslateY(((90 + 32) * Sprite.SCALE));
        Font font = Font.loadFont(MainMenu.class.getResourceAsStream(URL_FONT2), (FONT_SIZE + Sprite.SCALE));
        Text mText = new Text("MUSIC");
        mText.setFont(font);
        mText.setFill(Color.BROWN);
        Text sText = new Text("SOUNDFX");
        sText.setFont(font);
        sText.setFill(Color.BROWN);
        ImageView btnOn1 = setUpImageView("/menu/button_on.png", 0, 0, 22 * Sprite.SCALE, 10 * Sprite.SCALE);
        ImageView btnOn2 = setUpImageView("/menu/button_on.png", 0, 0, 22 * Sprite.SCALE, 10 * Sprite.SCALE);
        ImageView btnOff1 = setUpImageView("/menu/button_off.png", 0, 0, 22 * Sprite.SCALE, 10 * Sprite.SCALE);
        ImageView btnOff2 = setUpImageView("/menu/button_off.png", 0, 0, 22 * Sprite.SCALE, 10 * Sprite.SCALE);

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
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            Board.soundFX = false;
            soundBox.getChildren().remove(btnOn2);
            soundBox.getChildren().add(btnOff2);
        });
        btnOff2.setOnMouseClicked(event -> {
            Board.soundFX = true;
            soundBox.getChildren().remove(btnOff2);
            soundBox.getChildren().add(btnOn2);
        });

        int btnWidth = (BUTTON_WIDTH / 3) * 2;
        MenuButton btnExit = new MenuButton("EXIT", btnWidth,
                BUTTON_HEIGHT, FONT_SIZE, URL_FONT2);
        MenuButton btnResume = new MenuButton("RESUME", btnWidth,
                BUTTON_HEIGHT, FONT_SIZE, URL_FONT2);

        btnExit.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            returnMenu(scrW, scrH, root, lvLoad);
        });

        btnResume.setOnMouseClicked(event -> {
            if (Board.soundFX) {
                Sound.buttonClickAudio.play();
            }
            Board.Pause = false;
        });

        HBox btnBox = new HBox(20);
        btnBox.getChildren().addAll(btnExit, btnResume);
        btnBox.setTranslateX((scrW - (btnWidth * 2 + 20)) / 2);
        btnBox.setTranslateY(150 * Sprite.SCALE);

        getChildren().addAll(bg, menuView, musicBox, soundBox, btnBox);
    }

    public void returnMenu(double scrW, double scrH, Group root, LevelLoader lvLoad) {
        lvLoad.board = new Board();
        MainMenu mainMenu = new MainMenu();
        mainMenu.setUpMainMenu(scrW, scrH, lvLoad);
        root.getChildren().clear();
        root.getChildren().add(mainMenu);
        Board.Pause = false;
    }
}
