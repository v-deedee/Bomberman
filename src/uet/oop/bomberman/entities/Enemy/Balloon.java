package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.objects.Board;
import uet.oop.bomberman.graphics.PointImage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;

public class Balloon extends Enemy {
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;

    public Balloon(double x, double y, Image img) {
        super(x, y, img);
        point = 100;
    }

    @Override
    public void update() {
        if (isExploded) balloonDeadSprite();
    }

    public void setMovingSprite() {
        if (speedX > 0) {
            if (cntRightFrame >= 0 && cntRightFrame <= 9) {
                img = Sprite.balloom_right1.getFxImage();
            } else if (cntRightFrame >= 10 && cntRightFrame <= 19) {
                img = Sprite.balloom_right2.getFxImage();
            } else if (cntRightFrame >= 20 && cntRightFrame <= 29) {
                img = Sprite.balloom_right3.getFxImage();
            } else {
                cntRightFrame = -1;
            }
            cntRightFrame++;
        }
        if (speedX < 0) {
            if (cntLeftFrame >= 0 && cntLeftFrame <= 9) {
                img = Sprite.balloom_left1.getFxImage();
            } else if (cntLeftFrame >= 10 && cntLeftFrame <= 19) {
                img = Sprite.balloom_left2.getFxImage();
            } else if (cntLeftFrame >= 20 && cntLeftFrame <= 29) {
                img = Sprite.balloom_left3.getFxImage();
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

    public void balloonDeadSprite() {
        if (cntEnemyFrame >= 0 && cntEnemyFrame <= 19) {
            img = Sprite.balloom_dead.getFxImage();
        } else if (cntEnemyFrame >= 20 && cntEnemyFrame <= 39) {
            img = Sprite.mob_dead1.getFxImage();
        } else if (cntEnemyFrame >= 40 && cntEnemyFrame <= 59) {
            img = Sprite.mob_dead2.getFxImage();
        } else if (cntEnemyFrame >= 60 && cntEnemyFrame <= 79) {
            img = Sprite.mob_dead3.getFxImage();
        } else if (cntEnemyFrame >= 80 && cntEnemyFrame <= 120) {
            img = PointImage.point100;
        } else {
            isRemoved = true;
        }
        cntEnemyFrame++;
    }
}
