package uet.oop.bomberman;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Bomber;

import java.util.HashSet;

public class InputHandler {
    public final int step = 2;
    public HashSet<String> currentlyActiveKeys;

    public void prepareActionHandlers(Scene scene) {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    public void handleInput(Bomber bomberman) {
        if (currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A")) {
            bomberman.setX(bomberman.getX() - step);
        }
        if (currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D")) {
            bomberman.setX(bomberman.getX() + step);
        }
        if (currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W")) {
            bomberman.setY(bomberman.getY() - step);
        }
        if (currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S")) {
            bomberman.setY(bomberman.getY() + step);
        }
        System.out.println(currentlyActiveKeys); // test
    }
}
