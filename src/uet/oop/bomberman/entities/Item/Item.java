package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public boolean isRemoved = false;
    public Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public abstract void eaten();
}
