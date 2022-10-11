package uet.oop.bomberman;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.shape.*;

public class Menu extends Parent {
    private boolean isStart = false;

    public boolean getIsStart() {
        return isStart;
    }

    void startGame() {
        isStart = true;
    }

    public Menu(double scrW, double scrH) {
        VBox menu0 = new VBox(10);
        //VBox menu1 = new VBox(10);

        menu0.setTranslateX((scrW - 150) / 2);
        menu0.setTranslateY(scrH / 2);

        MenuButton btnStart = new MenuButton("START");
        btnStart.setOnMouseClicked(event -> {
            startGame();
        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu0.getChildren().addAll(btnStart, btnExit);
        Rectangle bg = new Rectangle(scrW, scrH);
        bg.setFill(Color.GREY);
        bg.setOpacity(1);

        getChildren().addAll(bg, menu0);
    }
}
