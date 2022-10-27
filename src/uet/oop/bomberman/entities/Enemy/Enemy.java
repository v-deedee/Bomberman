package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    public boolean isRemoved = false;
    protected int point;
    int cntEnemyFrame = 0;
    public boolean isExploded = false;
    protected int speedX = Sprite.SCALED_SIZE/32;
    protected int speedY = Sprite.SCALED_SIZE/32;
    List<Character> direction = new ArrayList<>();
    protected int rand;
    public Enemy(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public int getPoint() {
        return point;
    }

    public boolean canMoveLeft(LevelLoader lvLoad, int x1, int y1) {
        if (!(lvLoad.getMap(y1, x1 - 1) != '#' && lvLoad.getMap(y1, x1 - 1) != '*'
                && lvLoad.getMap(y1, x1 - 1) != 'x')) {
            return false;
        }
        for (Bomb bomb : lvLoad.board.bombs) {
            if ((x1 - 1) * Sprite.SCALED_SIZE == bomb.getX() && y1 * Sprite.SCALED_SIZE == bomb.getY()) {
                return false;
            }
        }
        for (Brick brick : lvLoad.board.bricks) {
            if ((x1 - 1) * Sprite.SCALED_SIZE == brick.getX() && y1 * Sprite.SCALED_SIZE == brick.getY()) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight(LevelLoader lvLoad, int x1, int y1) {
        if (!(lvLoad.getMap(y1, x1 + 1) != '#' && lvLoad.getMap(y1, x1 + 1) != '*'
                && lvLoad.getMap(y1, x1 + 1) != 'x')) {
            return false;
        }
        for (Bomb bomb : lvLoad.board.bombs) {
            if ((x1 + 1) * Sprite.SCALED_SIZE == bomb.getX() && y1 * Sprite.SCALED_SIZE == bomb.getY()) {
                return false;
            }
        }
        for (Brick brick : lvLoad.board.bricks) {
            if ((x1 + 1) * Sprite.SCALED_SIZE == brick.getX() && y1 * Sprite.SCALED_SIZE == brick.getY()) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveUp(LevelLoader lvLoad, int x1, int y1) {
        if (!(lvLoad.getMap(y1 - 1, x1) != '#' && lvLoad.getMap(y1 - 1, x1) != '*'
                && lvLoad.getMap(y1 - 1, x1) != 'x')) {
            return false;
        }
        for (Bomb bomb : lvLoad.board.bombs) {
            if (x1 * Sprite.SCALED_SIZE == bomb.getX() && (y1 - 1) * Sprite.SCALED_SIZE == bomb.getY()) {
                return false;
            }
        }
        for (Brick brick : lvLoad.board.bricks) {
            if (x1 * Sprite.SCALED_SIZE == brick.getX() && (y1 - 1) * Sprite.SCALED_SIZE == brick.getY()) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown(LevelLoader lvLoad, int x1, int y1) {
        if (!(lvLoad.getMap(y1 + 1, x1) != '#' && lvLoad.getMap(y1 + 1, x1) != '*'
                && lvLoad.getMap(y1 + 1, x1) != 'x')) {
            return false;
        }
        for (Bomb bomb : lvLoad.board.bombs) {
            if (x1 * Sprite.SCALED_SIZE == bomb.getX() && (y1 + 1) * Sprite.SCALED_SIZE == bomb.getY()) {
                return false;
            }
        }
        for (Brick brick : lvLoad.board.bricks) {
            if (x1 * Sprite.SCALED_SIZE == brick.getX() && (y1 + 1) * Sprite.SCALED_SIZE == brick.getY()) {
                return false;
            }
        }
        return true;
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

    public double calculateDistance(LevelLoader lvLoad) {
            // Get position in map
        if(lvLoad.board.bombers.size() != 0 && (int)x % Sprite.SCALED_SIZE == 0 && (int)y % Sprite.SCALED_SIZE == 0) {
            Bomber bomber = lvLoad.board.bombers.get(0);
            int enemyPosX = (int) (x / Sprite.SCALED_SIZE);
            int enemyPosY = (int) (y / Sprite.SCALED_SIZE);
            int bomberPosX = (int)(bomber.getX()/Sprite.SCALED_SIZE);
            int bomberPosY = (int)(bomber.getY()/Sprite.SCALED_SIZE);
            return Math.sqrt((bomberPosX - enemyPosX) * (bomberPosX - enemyPosX)
                    + (bomberPosY - enemyPosY) * (bomberPosY - enemyPosY));
        }
        else return 1000;
    }
}
