package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public boolean isRemoved = false;
    int cntFlameFrame = 10;
    public Flame(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        flameSprite();
    }

    public void flameSprite()
    {
        if(cntFlameFrame<=6 && cntFlameFrame>=4)
        {
            img = Sprite.bomb_exploded1.getFxImage();
        }
        else if(cntFlameFrame<=3 && cntFlameFrame>=0)
        {
            img = Sprite.bomb_exploded2.getFxImage();
        }
        else if (cntFlameFrame < 0) {
            isRemoved = true;
        }
        cntFlameFrame--;
    }
}
