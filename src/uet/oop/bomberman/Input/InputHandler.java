package uet.oop.bomberman.Input;

import javafx.scene.Scene;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Character.Bomber;

import java.util.*;

public class InputHandler {
    public Set<String> currentlyActiveKeys;
    int bombPlantCD = 15;

    public void prepareActionHandlers(Scene scene) {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new LinkedHashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    public void handleInput(Bomber bomberman, Board board) {
        bombPlantCD--;
        String[] activeKeysArr = currentlyActiveKeys.toArray(new String[0]);
        if (activeKeysArr.length != 0) {
            bomberman._direction = activeKeysArr[activeKeysArr.length - 1];
            if (currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A")) {
                bomberman.moveLeft();
                bomberman._moving = true;
            }
            if (currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D")) {
                bomberman.moveRight();
                bomberman._moving = true;
            }
            if (currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W")) {
                bomberman.moveUp();
                bomberman._moving = true;
            }
            if (currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S")) {
                bomberman.moveDown();
                bomberman._moving = true;
            }
            bomberman.movingSprite(bomberman._direction);
            if(currentlyActiveKeys.contains("SPACE") && bombPlantCD <= 0) {
                bomberman.plantBomb(board);
                bombPlantCD = 15;
            }
        }
        else bomberman._moving = false;
        if(!bomberman._moving)
        {
            bomberman.setAllFrameCnt();
            bomberman.movingSprite(bomberman._direction);
        }
//        System.out.println(currentlyActiveKeys); // test
    }
}
