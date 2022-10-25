package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class S_Item extends Item{
    public S_Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void eaten() {
        Bomber.step += Sprite.SCALED_SIZE/64.0;
    }

    @Override
    public void update() {

    }


}
