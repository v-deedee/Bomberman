package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

public class Oneal extends Enemy {
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;
    protected int speedX = 2;
    protected int speedY = 2;



    public Oneal(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isExploded) onealDeadSprite();
    }

    public void setMovingSprite() {
        if (speedX > 0) {
            if (cntRightFrame >= 0 && cntRightFrame <= 9) {
                img = Sprite.oneal_right1.getFxImage();
            } else if (cntRightFrame >= 10 && cntRightFrame <= 19) {
                img = Sprite.oneal_right2.getFxImage();
            } else if (cntRightFrame >= 20 && cntRightFrame <= 29) {
                img = Sprite.oneal_right3.getFxImage();
            } else {
                cntRightFrame = -1;
            }
            cntRightFrame++;
        }
        if (speedX < 0) {
            if (cntLeftFrame >= 0 && cntLeftFrame <= 9) {
                img = Sprite.oneal_left1.getFxImage();
            } else if (cntLeftFrame >= 10 && cntLeftFrame <= 19) {
                img = Sprite.oneal_left2.getFxImage();
            } else if (cntLeftFrame >= 20 && cntLeftFrame <= 29) {
                img = Sprite.oneal_left3.getFxImage();
            } else {
                cntLeftFrame = -1;
            }
            cntLeftFrame++;
        }
    }

    public void move(LevelLoader lvLoad) {
        if(Board.Pause) return;
        if (!isExploded) {
            enemyMove(lvLoad);
            setMovingSprite();
        }
    }

    public void onealDeadSprite() {
        if (cntEnemyFrame >= 0 && cntEnemyFrame <= 19) {
            img = Sprite.oneal_dead.getFxImage();
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
