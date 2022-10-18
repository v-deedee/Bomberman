package uet.oop.bomberman;

import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.shape.*;
import uet.oop.bomberman.graphics.Sprite;

import java.net.URISyntaxException;

public class Menu extends Parent {
    private static boolean isStart = false;

    public static boolean getIsStart() {
        return isStart;
    }

    void startGame() {
        isStart = true;
    }

    public Menu(double scrW, double scrH) {
        VBox menu0 = new VBox(20);
        //VBox menu1 = new VBox(10);

        menu0.setTranslateX((MenuButton.BUTTON_WIDTH) / 2.0);
        menu0.setTranslateY((scrH + MenuButton.BUTTON_HEIGHT) / 2);

        MenuButton btnStart = new MenuButton("START");
        btnStart.setOnMouseClicked(event -> {
            startGame();
        });

        MenuButton btnStages = new MenuButton("STAGES");
        btnStages.setOnMouseClicked(event -> {

        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu0.getChildren().addAll(btnStart, btnStages, btnExit);
        Rectangle bg = new Rectangle(scrW, scrH);
        bg.setFill(Color.GREY);
        bg.setOpacity(1);

        Image img = null;
        try {
            img = new Image(Menu.class.getResource("/GameBackground.png").toURI().toString(), true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(scrH);
        imgView.setFitWidth(scrW);

        getChildren().addAll(imgView, menu0);
    }
}
