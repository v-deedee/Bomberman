package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.objects.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    int cntBombFrame = 0;
    public boolean isRemoved = false;
    int explodeTime = 120;

    public Bomb(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void update() {
        if (Board.Pause) return;
        bombSprite();
        explodeTime--;
        if (explodeTime <= 0) isRemoved = true;
    }

    public void bombSprite() {
        if (cntBombFrame >= 0 && cntBombFrame <= 20) {
            img = Sprite.bomb.getFxImage();
        } else if (cntBombFrame >= 21 && cntBombFrame <= 40) {
            img = Sprite.bomb_1.getFxImage();
        } else if (cntBombFrame >= 41 && cntBombFrame <= 60) {
            img = Sprite.bomb_2.getFxImage();
        } else {
            cntBombFrame = -1;
        }
        cntBombFrame++;
    }
}
