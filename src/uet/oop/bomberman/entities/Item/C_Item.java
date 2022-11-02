package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.objects.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class C_Item extends Item {
    public C_Item(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void eaten() {
        BombermanGame.countdown.addTime();
    }

    @Override
    public void update() {

    }

    public void render(GraphicsContext gc, double distance) {
        gc.drawImage(img, x - distance, y + 2 * Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
}
