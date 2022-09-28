package uet.oop.bomberman.Input;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.levels.LevelLoader;

import java.util.*;

public class InputHandler {
    public Set<String> currentlyActiveKeys;

    public void prepareActionHandlers(Scene scene) {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new LinkedHashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    public void handleInput(Bomber bomberman, LevelLoader lvLoad) {
        String[] activeKeysArr = currentlyActiveKeys.toArray(new String[0]);
        if (activeKeysArr.length != 0) {
            String _input = activeKeysArr[activeKeysArr.length - 1];
            if (currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A")) {
                bomberman.moveLeft();
                bomberman.checkCollisionLeft(lvLoad);
            }
            if (currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D")) {
                bomberman.moveRight();
                bomberman.checkCollisionRight(lvLoad);
            }
            if (currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W")) {
                bomberman.moveUp();
                bomberman.checkCollisionUp(lvLoad);
            }
            if (currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S")) {
                bomberman.moveDown();
                bomberman.checkCollisionDown(lvLoad);
            }

            bomberman.movingSprite(_input);
        }
//        System.out.println(currentlyActiveKeys); // test
    }
}
