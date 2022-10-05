package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

public class Bomber extends Entity {

    public final double step = 2;
    public int cntUpFrame = 0;
    public int cntDownFrame = 0;
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;

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
        int x_ = (int)Math.ceil(x / 32.0);
        int x2 = (int)Math.floor(x / 32.0);
        int y_ = (int)Math.round(y / 32.0);
        if (y_ == 0) {
            y_++;
        }
        if (lvLoad.getMap(y_ - 1, x_) != ' ' && lvLoad.getMap(y_ - 1, x_) != 'p' && (int)(y + 2) % 32 == 0) {
            moveDown();
        }
        if (lvLoad.getMap(y_ - 1, x2) != ' ' && lvLoad.getMap(y_ - 1, x2) != 'p' && (int)(y + 2) % 32 == 0) {
            moveDown();
        }
    }

    public void checkCollisionLeft(LevelLoader lvLoad) {
        int x_ = (int)Math.round(x / 32.0);
        int y_ = (int)Math.ceil(y / 32.0);
        int y2 = (int)Math.floor(y / 32.0);
        if (x_ == 0) {
            x_++;
        }
        if (lvLoad.getMap(y_, x_ - 1) != ' ' && lvLoad.getMap(y_ , x_ - 1) != 'p' && (int)(x + 2) % 32 == 0) {
            moveRight();
        }
        if (lvLoad.getMap(y2, x_ - 1) != ' ' && lvLoad.getMap(y2 , x_ - 1) != 'p' && (int)(x + 2) % 32 == 0) {
            moveRight();
        }
    }

    public void checkCollisionDown(LevelLoader lvLoad) {
        int x_ = (int)Math.ceil(x / 32.0);
        int x2 = (int)Math.floor(x / 32.0);
        int y_ = (int)Math.round(y / 32.0);
        if (lvLoad.getMap(y_ + 1, x_) != ' ' && lvLoad.getMap(y_ + 1, x_) != 'p' && (int)(y - 2) % 32 == 0) {
            moveUp();
        }
        if (lvLoad.getMap(y_ + 1, x2) != ' ' && lvLoad.getMap(y_ + 1, x2) != 'p' && (int)(y - 2) % 32 == 0) {
            moveUp();
        }
    }

    public void checkCollisionRight(LevelLoader lvLoad) {
        int x_ = (int)Math.round(x / 32.0);
        int y_ = (int)Math.ceil(y / 32.0);
        int y2 = (int)Math.floor(y / 32.0);
        if (lvLoad.getMap(y_, x_ + 1) != ' ' && lvLoad.getMap(y_ , x_ + 1) != 'p' && (int)(x - 2) % 32 == 0) {
            moveLeft();
        }
        if (lvLoad.getMap(y2, x_ + 1) != ' ' && lvLoad.getMap(y2 , x_ + 1) != 'p' && (int)(x - 2) % 32 == 0) {
            moveLeft();
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
}