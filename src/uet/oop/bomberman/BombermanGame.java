package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import uet.oop.bomberman.Input.InputHandler;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.IntroLevel;
import uet.oop.bomberman.levels.LevelLoader;

public class BombermanGame extends Application {
    private LevelLoader lvLoad = new LevelLoader(1);
    //    Board board = lvLoad.board;
    static Group root = new Group();

    static Scene scene = new Scene(root);

    private final String TITLE = "BombermanGame";

    private final InputHandler _input = new InputHandler();

    private GraphicsContext gc;
    private Canvas canvas;

    // fps counter
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean frameArrFilled = false;
    private double frameRate;

    //
    private Menu menu;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE + "| Current Frame Rate: " + frameRate);
        stage.getIcons().add(new Image("/textures/icon.jfif"));
        // Tao Canvas
        canvas = new Canvas(lvLoad.getWidth() * Sprite.SCALED_SIZE / 2.0, lvLoad.getHeight() * Sprite.SCALED_SIZE);
        gc = canvas.getGraphicsContext2D();
        menu = new Menu(canvas.getWidth(), canvas.getHeight());

        // Tao root container
        root.getChildren().addAll(canvas, menu);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        _input.prepareActionHandlers(scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!lvLoad.board.bombers.isEmpty()) {
                    _input.handleInput(lvLoad.board.bombers.get(0), lvLoad.board, lvLoad);
                }
                if (Menu.getIsStart()) {
                    root.getChildren().clear();
                    root.getChildren().add(canvas);
                    lvLoad.introLevel.show(gc, canvas.getWidth(), canvas.getHeight());
                    if (!lvLoad.introLevel.getShowIntro()) {
                        render();
                        update();
                    }
                }

                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) frameArrFilled = true;
                if (frameArrFilled) {
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
                }
                stage.setTitle(TITLE + "| " + (int) frameRate + " rates");
            }
        };
        timer.start();
    }

    public void update() {
        lvLoad.board.updateAllEntity(lvLoad);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        lvLoad.board.renderAllEntity(gc);
    }
}