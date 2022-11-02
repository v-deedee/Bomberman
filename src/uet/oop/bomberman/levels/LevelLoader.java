package uet.oop.bomberman.levels;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.*;
import uet.oop.bomberman.entities.Item.*;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Portal;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    protected int width;
    protected int height;

    public static final int SCREEN_WIDTH = 31 * Sprite.SCALED_SIZE / 2;
    public static final int SCREEN_HEIGHT = (13 + 2) * Sprite.SCALED_SIZE;

    public Board board = new Board();
    public IntroLevel introLevel = new IntroLevel();
    private static char[][] map;

    public LevelLoader() {
    }

    public LevelLoader(int level) {
        loadLevel(level);
    }

    public char getMap(int i, int j) {
        return map[i][j];
    }

    public void setMap(int i, int j, char c) {
        map[i][j] = c;
    }

    public void loadLevel(int level) {
        board = new Board();
        Bomber.bombRadius = 1;
        Bomber.maxBomb = 1;
        Bomber.step = 2;
        Bomber.flamePass = false;
        introLevel.load("STAGE " + level);
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] levelInfo = list.get(0).trim().split(" ");
        height = Integer.parseInt(levelInfo[1]);
        width = Integer.parseInt(levelInfo[2]);
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        addEntity();
    }

    public void addEntity() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (map[y][x]) {
                    case '#':
                        board.addWall(new Wall(x, y, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        board.addPortal(new Portal(x, y, Sprite.portal.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'p':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addBomber(new Bomber(x, y, Sprite.player_right.getFxImage()));
                        break;
                    case 'b':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new B_Item(x, y, Sprite.powerup_bombs.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'f':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new F_Item(x, y, Sprite.powerup_flames.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 's':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new S_Item(x, y, Sprite.powerup_speed.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'o':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new FP_Item(x, y, Sprite.powerup_flamepass.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'c':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new C_Item(x, y, Sprite.powerup_clock));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case '1':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEnemy(new Balloon(x, y, Sprite.balloom_right1.getFxImage()));
                        setMap(y, x, ' ');
                        break;
                    case '2':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEnemy(new Oneal(x, y, Sprite.oneal_right1.getFxImage()));
                        setMap(y, x, ' ');
                        break;
                    case '3':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEnemy(new Doll(x, y, Sprite.doll_right1.getFxImage()));
                        setMap(y, x, ' ');
                        break;
                    case '4':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEnemy(new Minvo(x, y, Sprite.minvo_right1.getFxImage()));
                        setMap(y, x, ' ');
                        break;
                    case '5':
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEnemy(new Kondoria(x, y, Sprite.kondoria_right1.getFxImage()));
                        setMap(y, x, ' ');
                        break;
                    default:
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                }
            }
        }
    }

}
