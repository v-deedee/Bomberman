package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static final int step = 2;

    static Group root = new Group();

    static Scene scene = new Scene(root);

    private final String TITLE = "BombermanGame";
    
    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();

    // input handler
    static HashSet<String> currentlyActiveKeys;

    // fps counter
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean frameArrFilled = false;
    private double frameRate;

    Entity bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE + "| Current Frame Rate: " + frameRate);
        stage.getIcons().add(new Image("/textures/icon.jfif"));
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root.getChildren().add(canvas);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        prepareActionHandlers();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleInput();
                render();
                update();

                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex+1) % frameTimes.length;
                if(frameTimeIndex == 0) frameArrFilled = true;
                if(frameArrFilled)
                {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                }
                stage.setTitle(TITLE + "| " + (int)frameRate + " rates");
            }
        };
        timer.start();

        createMap();

        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void handleInput()
    {
        if(currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A"))
        {
            bomberman.setX(bomberman.getX() - step);
        }
        if(currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D"))
        {
            bomberman.setX(bomberman.getX() + step);
        }
        if(currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W"))
        {
            bomberman.setY(bomberman.getY() - step);
        }
        if(currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S"))
        {
            bomberman.setY(bomberman.getY() + step);
        }
        System.out.println(currentlyActiveKeys); // test
    }
    private static void prepareActionHandlers()
    {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }
}