package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

public class Oneal extends Enemy {
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;
    public Oneal(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(isExploded) onealDeadSprite();
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
        enemyMove(lvLoad);
        setMovingSprite();
    }

    public void onealDeadSprite() {
        if (cntEnemyFrame >= 0 && cntEnemyFrame <= 14) {
            img = Sprite.oneal_dead.getFxImage();
        } else {
            isRemoved = true;
        }
        cntEnemyFrame++;
    }
}
