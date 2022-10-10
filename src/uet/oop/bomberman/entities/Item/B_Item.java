package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;

public class B_Item  extends Item {
    public B_Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void eaten() {
        Bomber.maxBomb++;
    }

    @Override
    public void update() {

    }
}
