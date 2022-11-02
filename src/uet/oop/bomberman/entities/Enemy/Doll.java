package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.graphics.PointImage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

public class Doll extends Enemy {
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;
    double moveH = 0;
    double moveV = Sprite.SCALED_SIZE / 32.0;

    public Doll(double x, double y, Image img) {
        super(x, y, img);
        point = 300;
    }

    public void update() {
        if (isExploded) dollDeadSprite();
    }

    public void setMovingSprite() {
        if (speedX > 0) {
            if (cntRightFrame >= 0 && cntRightFrame <= 9) {
                img = Sprite.doll_right1.getFxImage();
            } else if (cntRightFrame >= 10 && cntRightFrame <= 19) {
                img = Sprite.doll_right2.getFxImage();
            } else if (cntRightFrame >= 20 && cntRightFrame <= 29) {
                img = Sprite.doll_right3.getFxImage();
            } else {
                cntRightFrame = -1;
            }
            cntRightFrame++;
        }
        if (speedX < 0) {
            if (cntLeftFrame >= 0 && cntLeftFrame <= 9) {
                img = Sprite.doll_left1.getFxImage();
            } else if (cntLeftFrame >= 10 && cntLeftFrame <= 19) {
                img = Sprite.doll_left2.getFxImage();
            } else if (cntLeftFrame >= 20 && cntLeftFrame <= 29) {
                img = Sprite.doll_left3.getFxImage();
            } else if (cntEnemyFrame >= 80 && cntEnemyFrame <= 120) {
                img = PointImage.point300;
            } else {
                cntLeftFrame = -1;
            }
            cntLeftFrame++;
        }
    }

    public void move(LevelLoader lvLoad) {
        if (Board.Pause) return;
        if (!isExploded) {
            enemyMove(lvLoad);
            setMovingSprite();
        }
    }

    public void dollDeadSprite() {
        if (cntEnemyFrame >= 0 && cntEnemyFrame <= 19) {
            img = Sprite.doll_dead.getFxImage();
        } else if (cntEnemyFrame >= 20 && cntEnemyFrame <= 39) {
            img = Sprite.mob_dead1.getFxImage();
        } else if (cntEnemyFrame >= 40 && cntEnemyFrame <= 59) {
            img = Sprite.mob_dead2.getFxImage();
        } else if (cntEnemyFrame >= 60 && cntEnemyFrame <= 79) {
            img = Sprite.mob_dead3.getFxImage();
        } else {
            isRemoved = true;
        }
        cntEnemyFrame++;
    }

    public void enemyMove(LevelLoader lvLoad) {
        try {
            int x1 = (int) x / Sprite.SCALED_SIZE;
            int y1 = (int) y / Sprite.SCALED_SIZE;
            if ((int) x % Sprite.SCALED_SIZE == 0 && (int) y % Sprite.SCALED_SIZE == 0) {
                int xp = (int) (lvLoad.board.bombers.get(0).getX() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE; // check bomber alive
                int yp = (int) (lvLoad.board.bombers.get(0).getY() + Sprite.DEFAULT_SIZE) / Sprite.SCALED_SIZE;
                if (xp >= x1 && speedX < 0) {
                    speedX = -speedX;
                }
                if (xp < x1 && speedX > 0) {
                    speedX = -speedX;
                }
                if (y1 == yp) {
                    moveH = 0;
                } else if (yp < y1 && canMoveUp(lvLoad, x1, y1)) {
                    moveH = -Sprite.SCALED_SIZE / 32.0;
                } else if (yp > y1 && canMoveDown(lvLoad, x1, y1)) {
                    moveH = Sprite.SCALED_SIZE / 32.0;
                }

                if (x1 == xp) {
                    moveV = 0;
                } else if (xp < x1 && canMoveLeft(lvLoad, x1, y1)) {
                    moveV = -Sprite.SCALED_SIZE / 32.0;
                } else if (xp > x1 && canMoveRight(lvLoad, x1, y1)) {
                    moveV = Sprite.SCALED_SIZE / 32.0;
                }
                if ((moveV > 0 && !canMoveRight(lvLoad, x1, y1)) || (moveV < 0 && !canMoveLeft(lvLoad, x1, y1))) {
                    moveV = 0;
                }
                if ((moveH > 0 && !canMoveDown(lvLoad, x1, y1)) || (moveH < 0 && !canMoveUp(lvLoad, x1, y1))) {
                    moveH = 0;
                }
                if (moveV != 0 && moveH != 0) {
                    if (Math.abs(xp - x1) > Math.abs(yp - y1)) {
                        moveH = 0;
                    } else {
                        moveV = 0;
                    }
                }

            }
            x += moveV;
            y += moveH;
        } catch (Exception e) {
            System.out.println(x + " " + y);
        }
    }
}
