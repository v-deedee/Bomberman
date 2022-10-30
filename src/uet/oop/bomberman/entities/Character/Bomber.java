package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

import java.awt.*;

public class Bomber extends Entity {

    public static int bombRadius = 1;
    public static double step = Sprite.SCALED_SIZE / 16.0;
    public static int maxBomb = 1;
    public static boolean flamePass = false;

    public boolean isDead = false;

    public boolean isRemoved = false;
    public boolean isDR = false;
    public boolean _moving = false;
    public String _direction = "RIGHT";
    public int cntUpFrame = 0;
    public int cntDownFrame = 0;
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;

    public int cntDeadFrame = 0;
    public int cntDRFrame = 0;

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
        if (isDead) bomberDeadSprite();
        if (isDR) bomberDeadAndReviveSprite();
    }

    public void moveUp() {
        if (!isDead && !isDR) {
            y = y - step;
        }
    }

    public void moveDown() {
        if (!isDead && !isDR) {
            y = y + step;
        }
    }

    public void moveRight() {
        if (!isDead && !isDR) {
            x = x + step;
        }
    }

    public void moveLeft() {
        if (!isDead && !isDR) {
            x = x - step;
        }
    }

    public void checkCollisionUp(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int) x / Sprite.SCALED_SIZE;
        int y1 = (int) y / Sprite.SCALED_SIZE;
        int scale = Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * scale;
        int bomberHeight = Sprite.player_down.get_realHeight() * scale;
        Rectangle bomber = new Rectangle((int) x, (int) y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.SCALED_SIZE;
        Rectangle obstacleTopLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleTopRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check up collision
        if ((lvLoad.getMap(y1, x1) != ' ' && lvLoad.getMap(y1, x1) != 'p'
                && bomber.getBounds().intersects(obstacleTopLeft.getBounds()))
                || (lvLoad.getMap(y1, (x1 + 1)) != ' ' && lvLoad.getMap(y1, (x1 + 1)) != 'p'
                && bomber.getBounds().intersects(obstacleTopRight.getBounds()))) {
            moveDown();
        }
    }

    public void checkCollisionLeft(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int) x / Sprite.SCALED_SIZE;
        int y1 = (int) y / Sprite.SCALED_SIZE;
        int scale = Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * scale;
        int bomberHeight = Sprite.player_down.get_realHeight() * scale;
        Rectangle bomber = new Rectangle((int) x, (int) y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.SCALED_SIZE;
        Rectangle obstacleTopLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleDownLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check left collision
        if ((lvLoad.getMap(y1, x1) != ' ' && lvLoad.getMap(y1, x1) != 'p'
                && bomber.getBounds().intersects(obstacleTopLeft.getBounds()))
                || (lvLoad.getMap((y1 + 1), x1) != ' ' && lvLoad.getMap((y1 + 1), x1) != 'p'
                && bomber.getBounds().intersects(obstacleDownLeft.getBounds()))) {
            moveRight();
        }
    }

    public void checkCollisionDown(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int) x / Sprite.SCALED_SIZE;
        int y1 = (int) y / Sprite.SCALED_SIZE;
        int scale = Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * scale;
        int bomberHeight = Sprite.player_down.get_realHeight() * scale;
        Rectangle bomber = new Rectangle((int) x, (int) y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.SCALED_SIZE;
        Rectangle obstacleDownLeft = new Rectangle(x1 * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleDownRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check down collision
        if ((lvLoad.getMap((y1 + 1), (x1 + 1)) != ' ' && lvLoad.getMap((y1 + 1), (x1 + 1)) != 'p'
                && bomber.getBounds().intersects(obstacleDownRight.getBounds()))
                || (lvLoad.getMap((y1 + 1), x1) != ' ' && lvLoad.getMap((y1 + 1), x1) != 'p'
                && bomber.getBounds().intersects(obstacleDownLeft.getBounds()))) {
            moveUp();
        }
    }

    public void checkCollisionRight(LevelLoader lvLoad) {
        // Get position in map
        int x1 = (int) x / Sprite.SCALED_SIZE;
        int y1 = (int) y / Sprite.SCALED_SIZE;
        int scale = Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE;
        // Get rectangle of bomber
        int bomberWidth = Sprite.player_down.get_realWidth() * scale;
        int bomberHeight = Sprite.player_down.get_realHeight() * scale;
        Rectangle bomber = new Rectangle((int) x, (int) y, bomberWidth, bomberHeight);
        // Create rectangle of obstacle
        int obstacleSize = Sprite.SCALED_SIZE;
        Rectangle obstacleTopRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                y1 * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        Rectangle obstacleDownRight = new Rectangle((x1 + 1) * Sprite.SCALED_SIZE,
                (y1 + 1) * Sprite.SCALED_SIZE, obstacleSize, obstacleSize);
        // Check right collision
        if ((lvLoad.getMap((y1 + 1), (x1 + 1)) != ' ' && lvLoad.getMap((y1 + 1), (x1 + 1)) != 'p'
                && bomber.getBounds().intersects(obstacleDownRight.getBounds()))
                || (lvLoad.getMap(y1, (x1 + 1)) != ' ' && lvLoad.getMap(y1, (x1 + 1)) != 'p'
                && bomber.getBounds().intersects(obstacleTopRight.getBounds()))) {
            moveLeft();
        }
    }

    public void movingSprite(String _input) {
        if (!isDead && !isDR) {
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

    public void bomberDeadSprite() {
        if (cntDeadFrame >= 0 && cntDeadFrame <= 10) {
            img = Sprite.player_dead1.getFxImage();
        } else if (cntDeadFrame >= 11 && cntDeadFrame <= 20) {
            img = Sprite.player_dead2.getFxImage();
        } else if (cntDeadFrame >= 21 && cntDeadFrame <= 30) {
            img = Sprite.player_dead3.getFxImage();
        } else {
            isRemoved = true;
        }
        cntDeadFrame++;
    }

    public void bomberDeadAndReviveSprite() {
        if (cntDRFrame >= 0 && cntDRFrame <= 10) {
            img = Sprite.player_dead1.getFxImage();
        } else if (cntDRFrame >= 11 && cntDRFrame <= 20) {
            img = Sprite.player_dead2.getFxImage();
        } else if (cntDRFrame >= 21 && cntDRFrame <= 30) {
            img = Sprite.player_dead3.getFxImage();
        } else if (cntDRFrame >= 31 && cntDRFrame <= 40) {
            img = Sprite.player_dead3.getFxImage();
        } else if (cntDRFrame >= 41 && cntDRFrame <= 50) {
            img = Sprite.player_dead2.getFxImage();
        } else if (cntDRFrame >= 51 && cntDRFrame <= 60) {
            img = Sprite.player_dead2.getFxImage();
        } else if (cntDRFrame >= 61 && cntDRFrame <= 70) {
            img = Sprite.player_right.getFxImage();
        } else {
            cntDRFrame = 0;
            isDR = false;
        }
        cntDRFrame++;
    }
}
