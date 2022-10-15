package uet.oop.bomberman.levels;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;

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

    public void load(String text) {
        try {
            font = Font.loadFont(IntroLevel.class.getResource("/font/Font1.ttf").toURI().toString(), FONT_SIZE);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            font = Font.getDefault().font(FONT_SIZE);
        }
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
