package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;

public class FP_Item extends Item {

    public FP_Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void eaten() {
        Bomber.flamePass = true;
    }
}
