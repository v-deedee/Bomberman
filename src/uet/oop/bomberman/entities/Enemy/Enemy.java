package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    protected int speedX = 1;
    protected int speedY = 1;
    List<Character> direction = new ArrayList<>();
    protected int rand;
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public boolean canMoveLeft(LevelLoader lvLoad, int x1, int y1) {
        return lvLoad.getMap(y1, x1 - 1) != '#' && lvLoad.getMap(y1, x1 - 1) != '*';
    }

    public boolean canMoveRight(LevelLoader lvLoad, int x1, int y1) {
        return lvLoad.getMap(y1, x1 + 1) != '#' && lvLoad.getMap(y1, x1 + 1) != '*';
    }

    public boolean canMoveUp(LevelLoader lvLoad, int x1, int y1) {
        return lvLoad.getMap(y1 - 1, x1) != '#' && lvLoad.getMap(y1 - 1, x1) != '*';
    }

    public boolean canMoveDown(LevelLoader lvLoad, int x1, int y1) {
        return lvLoad.getMap(y1 + 1, x1) != '#' && lvLoad.getMap(y1 + 1, x1) != '*';
    }

    public void enemyMove(LevelLoader lvLoad) {
        try {
            if ((int)x % Sprite.SCALED_SIZE == 0 && (int)y % Sprite.SCALED_SIZE == 0) {
                // Get position in map
                int x1 = (int)x / Sprite.SCALED_SIZE;
                int y1 = (int)y / Sprite.SCALED_SIZE;

                direction.clear();
                if (canMoveLeft(lvLoad, x1, y1) || canMoveRight(lvLoad, x1, y1)) {
                    direction.add('h');
                }
                if (canMoveUp(lvLoad, x1, y1) || canMoveDown(lvLoad, x1, y1)) {
                    direction.add('v');
                }
                if (direction.isEmpty()) {
                    return;
                }
                rand = (int)(Math.random() * 100) % direction.size();

                if (direction.get(rand) == 'h') {
                    if (speedX > 0) {
                        if (!canMoveRight(lvLoad, x1, y1)) {
                            speedX = -speedX;
                        }
                    } else {
                        if (!canMoveLeft(lvLoad, x1, y1)) {
                            speedX = -speedX;
                        }
                    }
                } else {
                    if (speedY > 0) {
                        if (!canMoveDown(lvLoad, x1, y1)) {
                            speedY = -speedY;
                        }
                    } else {
                        if (!canMoveUp(lvLoad, x1, y1)) {
                            speedY = -speedY;
                        }
                    }
                }
            }
            if (direction.get(rand) == 'h') {
                x += speedX;
            } else {
                y += speedY;
            }
        } catch (Exception e) {
            System.out.println(x + " " + y);
        }
    }
}
