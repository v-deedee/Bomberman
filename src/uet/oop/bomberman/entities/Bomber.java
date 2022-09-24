package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sun.security.jgss.wrapper.GSSCredElement;

import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends Entity {

    public final double step = 1.7;
    public int cntUpFrame = 0;
    public int cntDownFrame = 0;
    public int cntLeftFrame = 0;
    public int cntRightFrame = 0;
    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
    }

    public void moveUp()
    {
        y = y - step;
        if(cntUpFrame == 0)
        {
            cntDownFrame = 0;
            cntRightFrame = 0;
            cntLeftFrame = 0;
        }
        switch(cntUpFrame)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                img = Sprite.player_up.getFxImage();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                img = Sprite.player_up_1.getFxImage();
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                img = Sprite.player_up_2.getFxImage();
                break;
            default:
                cntUpFrame = -1;
                break;
        }
        cntUpFrame++;
    }

    public void moveDown()
    {
        y = y + step;
        if(cntDownFrame == 0)
        {
            cntUpFrame = 0;
            cntRightFrame = 0;
            cntLeftFrame = 0;
        }
        switch(cntDownFrame)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                img = Sprite.player_down.getFxImage();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                img = Sprite.player_down_1.getFxImage();
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                img = Sprite.player_down_2.getFxImage();
                break;
            default:
                cntDownFrame = -1;
                break;
        }
        cntDownFrame++;
    }

    public void moveRight()
    {
        x = x + step;
        if(cntRightFrame == 0)
        {
            cntDownFrame = 0;
            cntUpFrame = 0;
            cntLeftFrame = 0;
        }
        switch(cntRightFrame)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                img = Sprite.player_right.getFxImage();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                img = Sprite.player_right_1.getFxImage();
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                img = Sprite.player_right_2.getFxImage();
                break;
            default:
                cntRightFrame = -1;
                break;
        }
        cntRightFrame++;
    }

    public void moveLeft()
    {
        x = x - step;
        if(cntLeftFrame == 0)
        {
            cntDownFrame = 0;
            cntRightFrame = 0;
            cntUpFrame = 0;
        }
        switch(cntLeftFrame)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                img = Sprite.player_left.getFxImage();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                img = Sprite.player_left_1.getFxImage();
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                img = Sprite.player_left_2.getFxImage();
                break;
            default:
                cntLeftFrame = -1;
                break;
        }
        cntLeftFrame++;
    }
}
