package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;

public class F_Item extends Item{
    public F_Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void eaten() {
        Bomber.bombRadius++;
    }

    @Override
    public void update() {

    }
}
