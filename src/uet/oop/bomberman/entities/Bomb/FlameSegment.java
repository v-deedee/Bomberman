package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    public boolean isRemoved = false;
    int cntFlameSegmentFrame = 10;
    int flSegType = 0;

    public FlameSegment(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public FlameSegment(double x, double y, int direction, boolean last) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        switch (direction) {
            case 0:
                if (!last) {
                    img = Sprite.explosion_vertical.getFxImage();
                    flSegType = 0;
                } else {
                    img = Sprite.explosion_vertical_top_last.getFxImage();
                    flSegType = 1;
                }
                break;
            case 1:
                if (!last) {
                    img = Sprite.explosion_horizontal.getFxImage();
                    flSegType = 2;
                } else {
                    img = Sprite.explosion_horizontal_right_last.getFxImage();
                    flSegType = 3;
                }
                break;
            case 2:
                if (!last) {
                    img = Sprite.explosion_vertical.getFxImage();
                    flSegType = 4;
                } else {
                    img = Sprite.explosion_vertical_down_last.getFxImage();
                    flSegType = 5;
                }
                break;
            case 3:
                if (!last) {
                    img = Sprite.explosion_horizontal.getFxImage();
                    flSegType = 6;
                } else {
                    img = Sprite.explosion_horizontal_left_last.getFxImage();
                    flSegType = 7;
                }
                break;
        }
    }

    @Override
    public void update() {
        flameSegmentSprite();
    }

    public void flameSegmentSprite() {
        switch (flSegType) {
            case 0:
            case 4:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_vertical1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_vertical2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
            case 1:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_vertical_top_last1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
            case 2:
            case 6:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_horizontal1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_horizontal2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
            case 3:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_horizontal_right_last1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
            case 5:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_vertical_down_last1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
            case 7:
                if (cntFlameSegmentFrame <= 6 && cntFlameSegmentFrame >= 4) {
                    img = Sprite.explosion_horizontal_left_last1.getFxImage();
                } else if (cntFlameSegmentFrame <= 3 && cntFlameSegmentFrame >= 0) {
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                } else if (cntFlameSegmentFrame < 0) {
                    isRemoved = true;
                }
                break;
        }
        cntFlameSegmentFrame--;
    }
}
