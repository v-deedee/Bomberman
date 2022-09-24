package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sun.security.jgss.wrapper.GSSCredElement;

import java.util.ArrayList;

public class Bomber extends Entity {

    public final int step = 2;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
    }

    public void moveUp()
    {
        y = y - step;
    }

    public void moveDown()
    {
        y = y + step;
    }

    public void moveRight()
    {
        x = x + step;
    }

    public void moveLeft()
    {
        x = x - step;
    }
}
