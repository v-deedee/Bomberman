package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

import java.awt.*;

public class Bomber extends Entity {

    public final double step = 2;
    public boolean _moving = false;
    public String _direction = "RIGHT";
    public int cntUpFrame = 0;
    public int cntDownFrame = 0;
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;

    int maxBomb = 100;

    public void setAllFrameCnt() {
        cntUpFrame = 0;
        cntDownFrame = 0;
        cntLeftFrame = 0;
        cntRightFrame = 0;
    }

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }

    public void moveUp() {
        y = y - step;
    }

    public void moveDown() {
        y = y + step;
    }

    public void moveRight() {
        x = x + step;
    }

    public void moveLeft() {
        x = x - step;
    }

    public void checkCollisionUp(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int)x / Sprite.SCALED_SIZE;
        int y1 = (int)y / Sprite.SCALED_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * 2;
        int bomberHeight = Sprite.player_down.get_realHeight() * 2;
        Rectangle bomber = new Rectangle((int)x, (int)y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.wall.get_realWidth() * 2;
        Rectangle obstacleTopLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE,obstacleSize, obstacleSize);
        Rectangle obstacleTopRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check up collision
        if ((lvLoad.getMap(y1, x1) != ' ' && lvLoad.getMap(y1, x1) != 'p'
                && bomber.getBounds().intersects(obstacleTopLeft.getBounds()))
                || (lvLoad.getMap(y1, (x1 + 1)) != ' ' && lvLoad.getMap(y1, (x1 + 1)) != 'p'
                && bomber.getBounds().intersects(obstacleTopRight.getBounds()))){
            moveDown();
        }
    }

    public void checkCollisionLeft(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int)x / Sprite.SCALED_SIZE;
        int y1 = (int)y / Sprite.SCALED_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * 2;
        int bomberHeight = Sprite.player_down.get_realHeight() * 2;
        Rectangle bomber = new Rectangle((int)x, (int)y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.wall.get_realWidth() * 2;
        Rectangle obstacleTopLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE,obstacleSize, obstacleSize);
        Rectangle obstacleDownLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check left collision
        if ((lvLoad.getMap(y1, x1) != ' ' && lvLoad.getMap(y1, x1) != 'p'
                && bomber.getBounds().intersects(obstacleTopLeft.getBounds()))
                || (lvLoad.getMap((y1 + 1), x1) != ' ' && lvLoad.getMap((y1 + 1), x1) != 'p'
                && bomber.getBounds().intersects(obstacleDownLeft.getBounds()))){
            moveRight();
        }
    }

    public void checkCollisionDown(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int)x / Sprite.SCALED_SIZE;
        int y1 = (int)y / Sprite.SCALED_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * 2;
        int bomberHeight = Sprite.player_down.get_realHeight() * 2;
        Rectangle bomber = new Rectangle((int)x, (int)y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.wall.get_realWidth() * 2;
        Rectangle obstacleDownLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleDownRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check down collision
        if ((lvLoad.getMap((y1 + 1), (x1 + 1)) != ' '
                && bomber.getBounds().intersects(obstacleDownRight.getBounds()))
                || (lvLoad.getMap((y1 + 1), x1) != ' '
                && bomber.getBounds().intersects(obstacleDownLeft.getBounds()))){
            moveUp();
        }
    }

    public void checkCollisionRight(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int)x / Sprite.SCALED_SIZE;
        int y1 = (int)y / Sprite.SCALED_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * 2;
        int bomberHeight = Sprite.player_down.get_realHeight() * 2;
        Rectangle bomber = new Rectangle((int)x, (int)y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.wall.get_realWidth() * 2;
        Rectangle obstacleTopRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleDownRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check right collision
        if ((lvLoad.getMap((y1 + 1), (x1 + 1)) != ' '
                && bomber.getBounds().intersects(obstacleDownRight.getBounds()))
                || (lvLoad.getMap(y1, (x1 + 1)) != ' '
                && bomber.getBounds().intersects(obstacleTopRight.getBounds()))){
            moveLeft();
            return;
        }
    }

    public void movingSprite(String _input) {
        switch (_input) {
            case "LEFT":
            case "A":
                if (cntLeftFrame == 0) {
                    cntDownFrame = 0;
                    cntRightFrame = 0;
                    cntUpFrame = 0;
                }
                if (cntLeftFrame >= 0 && cntLeftFrame <= 4) {
                    img = Sprite.player_left.getFxImage();
                } else if (cntLeftFrame >= 5 && cntLeftFrame <= 9) {
                    img = Sprite.player_left_1.getFxImage();
                } else if (cntLeftFrame >= 10 && cntLeftFrame <= 14) {
                    img = Sprite.player_left_2.getFxImage();
                } else {
                    cntLeftFrame = -1;
                }
                cntLeftFrame++;
                break;
            case "RIGHT":
            case "D":
                if (cntRightFrame == 0) {
                    cntDownFrame = 0;
                    cntUpFrame = 0;
                    cntLeftFrame = 0;
                }
                if (cntRightFrame >= 0 && cntRightFrame <= 4) {
                    img = Sprite.player_right.getFxImage();
                } else if (cntRightFrame >= 5 && cntRightFrame <= 9) {
                    img = Sprite.player_right_1.getFxImage();
                } else if (cntRightFrame >= 10 && cntRightFrame <= 14) {
                    img = Sprite.player_right_2.getFxImage();
                } else {
                    cntRightFrame = -1;
                }
                cntRightFrame++;
                break;
            case "UP":
            case "W":
                if (cntUpFrame == 0) {
                    cntDownFrame = 0;
                    cntRightFrame = 0;
                    cntLeftFrame = 0;
                }
                if (cntUpFrame >= 0 && cntUpFrame <= 4) {
                    img = Sprite.player_up.getFxImage();
                } else if (cntUpFrame >= 5 && cntUpFrame <= 9) {
                    img = Sprite.player_up_1.getFxImage();
                } else if (cntUpFrame >= 10 && cntUpFrame <= 14) {
                    img = Sprite.player_up_2.getFxImage();
                } else {
                    cntUpFrame = -1;
                }
                cntUpFrame++;
                break;
            case "DOWN":
            case "S":
                if (cntDownFrame == 0) {
                    cntUpFrame = 0;
                    cntRightFrame = 0;
                    cntLeftFrame = 0;
                }
                if (cntDownFrame >= 0 && cntDownFrame <= 4) {
                    img = Sprite.player_down.getFxImage();
                } else if (cntDownFrame >= 5 && cntDownFrame <= 9) {
                    img = Sprite.player_down_1.getFxImage();
                } else if (cntDownFrame >= 10 && cntDownFrame <= 14) {
                    img = Sprite.player_down_2.getFxImage();
                } else {
                    cntDownFrame = -1;
                }
                cntDownFrame++;
                break;
        }
    }

    public void plantBomb(Board _board) {
        int sw = 0;
        double bombX = (int) (getX() / Sprite.SCALED_SIZE + 0.5);
        double bombY = (int) (getY() / Sprite.SCALED_SIZE + 0.5);
        Bomb tempBomb = new Bomb(bombX, bombY, Sprite.bomb.getFxImage());
        for (int i = 0; i < _board.bombs.size(); i++) {
            if (_board.bombs.get(i).getX() == bombX * Sprite.SCALED_SIZE
                    && _board.bombs.get(i).getY() == bombY * Sprite.SCALED_SIZE) sw++;
        }
        for (int i = 0; i < _board.walls.size(); i++) {
            if (_board.walls.get(i).getX() == bombX * Sprite.SCALED_SIZE
                    && _board.walls.get(i).getY() == bombY * Sprite.SCALED_SIZE) sw++;
        }
        for (int i = 0; i < _board.bricks.size(); i++) {
            if (_board.bricks.get(i).getX() == bombX * Sprite.SCALED_SIZE
                    && _board.bricks.get(i).getY() == bombY * Sprite.SCALED_SIZE) sw++;
        }
        for (int i = 0; i < _board.enemies.size(); i++) {
            if (_board.enemies.get(i).getX() == bombX * Sprite.SCALED_SIZE
                    && _board.enemies.get(i).getY() == bombY * Sprite.SCALED_SIZE) sw++;
        }
        if (_board.bombs.size() < maxBomb && sw == 0) {
            _board.addBomb(tempBomb);
        }
    }
}
