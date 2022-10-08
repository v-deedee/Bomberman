package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public int cntBrickFrame = 0;
    public boolean isRemoved = false;
    public boolean isExploded = false;
    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isExploded) {
            brickSprite();
        }
    }

    public void brickSprite() {
        if (cntBrickFrame >= 0 && cntBrickFrame <= 4) {
            img = Sprite.brick_exploded.getFxImage();
        } else if (cntBrickFrame >= 5 && cntBrickFrame <= 9) {
            img = Sprite.brick_exploded1.getFxImage();
        } else if (cntBrickFrame >= 10 && cntBrickFrame <= 14) {
            img = Sprite.brick_exploded2.getFxImage();
        } else {
            isRemoved = true;
        }
        cntBrickFrame++;
    }
}
