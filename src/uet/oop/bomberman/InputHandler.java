package uet.oop.bomberman;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Bomber;

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
        if(activeKeysArr.length != 0) {
            String _input = activeKeysArr[activeKeysArr.length - 1];
            if (_input.equals("LEFT") || _input.equals("A")) {
                bomberman.moveLeft();
            }
            if (_input.equals("RIGHT") || _input.equals("D")) {
                bomberman.moveRight();
            }
            if (_input.equals("UP") || _input.equals("W")) {
                bomberman.moveUp();
            }
            if (_input.equals("DOWN") || _input.equals("S")) {
                bomberman.moveDown();
            }
        }
        System.out.println(currentlyActiveKeys); // test
    }
}
