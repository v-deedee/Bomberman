package uet.oop.bomberman.Input;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Character.Bomber;

import java.util.*;

public class InputHandler {
    public Set<String> currentlyActiveKeys;

    public void prepareActionHandlers(Scene scene) {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new LinkedHashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    public void handleInput(Bomber bomberman) {
        String[] activeKeysArr = currentlyActiveKeys.toArray(new String[0]);
        if (activeKeysArr.length != 0) {
            String _input = activeKeysArr[activeKeysArr.length - 1];
            if (currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A")) {
                bomberman.moveLeft();
            }
            if (currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D")) {
                bomberman.moveRight();
            }
            if (currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W")) {
                bomberman.moveUp();
            }
            if (currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S")) {
                bomberman.moveDown();
            }
            bomberman.movingSprite(_input);
        }
        System.out.println(currentlyActiveKeys); // test
    }
}
