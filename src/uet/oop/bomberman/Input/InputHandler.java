package uet.oop.bomberman.Input;

import javafx.scene.Scene;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.levels.LevelLoader;

import java.util.*;

public class InputHandler {
    public Set<String> currentlyActiveKeys;
    public List<String> movementKey = new ArrayList<>();
    int bombPlantCD = 15;
    int pauseCD = 30;

    public void prepareActionHandlers(Scene scene) {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new LinkedHashSet<>();
        scene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        scene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));

        // set movementKey
        movementKey.add("LEFT");
        movementKey.add("RIGHT");
        movementKey.add("UP");
        movementKey.add("DOWN");
        movementKey.add("A");
        movementKey.add("S");
        movementKey.add("W");
        movementKey.add("D");
    }

    public void handleInput(Bomber bomberman, Board board, LevelLoader lvLoad) {
        pauseCD--;
        if (currentlyActiveKeys.contains("ESCAPE") && pauseCD <= 0) {
            Board.Pause = !Board.Pause;
            pauseCD = 30;
        }
        if (Board.Pause) return;
        bombPlantCD--;
        String[] activeKeysArr = currentlyActiveKeys.toArray(new String[0]);
        if (activeKeysArr.length != 0) {
            if (movementKey.contains(activeKeysArr[activeKeysArr.length - 1])) {
                bomberman._direction = activeKeysArr[activeKeysArr.length - 1];
            }
            if (currentlyActiveKeys.contains("LEFT") || currentlyActiveKeys.contains("A")) {
                bomberman.moveLeft();
                bomberman._moving = true;
                bomberman.checkCollisionLeft(lvLoad);
            }
            if (currentlyActiveKeys.contains("RIGHT") || currentlyActiveKeys.contains("D")) {
                bomberman.moveRight();
                bomberman._moving = true;
                bomberman.checkCollisionRight(lvLoad);
            }
            if (currentlyActiveKeys.contains("UP") || currentlyActiveKeys.contains("W")) {
                bomberman.moveUp();
                bomberman._moving = true;
                bomberman.checkCollisionUp(lvLoad);
            }
            if (currentlyActiveKeys.contains("DOWN") || currentlyActiveKeys.contains("S")) {
                bomberman.moveDown();
                bomberman._moving = true;
                bomberman.checkCollisionDown(lvLoad);
            }
            bomberman.movingSprite(bomberman._direction);
        } else bomberman._moving = false;
        if (!bomberman._moving) {
            bomberman.setAllFrameCnt();
            bomberman.movingSprite(bomberman._direction);
        }
        if (currentlyActiveKeys.contains("SPACE") && bombPlantCD <= 0) {
            bomberman.plantBomb(board);
            bombPlantCD = 15;
        }
    }
}
