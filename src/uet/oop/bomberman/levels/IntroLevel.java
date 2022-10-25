package uet.oop.bomberman.levels;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.graphics.Sprite;

import java.time.Duration;
import java.time.LocalDateTime;

public class IntroLevel {
    public static final int FONT_SIZE = 10 * Sprite.SCALE;
    private boolean showIntro;
    private String text;
    private Font font;
    private LocalDateTime startTime;
    private boolean setStartTime;

    public IntroLevel() {
        showIntro = true;
        setStartTime = true;
    }

    public boolean getShowIntro() {
        return showIntro;
    }

    public void setShowIntro(boolean showIntro) {
        this.showIntro = showIntro;
    }

    public void resetTime() {
        setStartTime = true;
    }

    public void load(String text) {
        font = Font.loadFont(IntroLevel.class.getResourceAsStream("/font/Font1.ttf"), FONT_SIZE);
        this.text = text;
    }

    public void show(GraphicsContext gc, double scrW, double scrH) {
        if (setStartTime) {
            startTime = LocalDateTime.now();
            setStartTime = false;
        }
        if (showIntro) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, scrW, scrH);
            gc.setFill(Color.WHITE);
            gc.setFont(font);
            gc.fillText(text, (scrW - FONT_SIZE * 5) / 2, scrH / 2);

            LocalDateTime endTime = LocalDateTime.now();
            if (Duration.between(startTime, endTime).getSeconds() > 2) {
                showIntro = false;
            }
        }
    }
}
