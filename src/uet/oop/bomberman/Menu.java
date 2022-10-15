package uet.oop.bomberman;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.shape.*;
import uet.oop.bomberman.graphics.Sprite;

public class Menu extends Parent {
    private static boolean isStart = false;

    public static boolean getIsStart() {
        return isStart;
    }

    void startGame() {
        isStart = true;
    }

    public Menu(double scrW, double scrH) {
        VBox menu0 = new VBox(10);
        //VBox menu1 = new VBox(10);

        menu0.setTranslateX((scrW - MenuButton.BUTTON_WIDTH) / 2);
        menu0.setTranslateY(scrH / 2);

        MenuButton btnStart = new MenuButton("START");
        btnStart.setOnMouseClicked(event -> {
            startGame();
        });

        MenuButton btnOptions = new MenuButton("OPTIONS");
        btnOptions.setOnMouseClicked(event -> {

        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu0.getChildren().addAll(btnStart, btnOptions, btnExit);
        Rectangle bg = new Rectangle(scrW, scrH);
        bg.setFill(Color.GREY);
        bg.setOpacity(1);

        getChildren().addAll(bg, menu0);
    }
}
