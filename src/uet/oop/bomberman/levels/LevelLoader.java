package uet.oop.bomberman.levels;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Balloon;
import uet.oop.bomberman.entities.Enemy.Oneal;
import uet.oop.bomberman.entities.Item.B_Item;
import uet.oop.bomberman.entities.Item.F_Item;
import uet.oop.bomberman.entities.Item.S_Item;
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
    protected static int width;
    protected static int height;
    public Board board = new Board();
    public IntroLevel introLevel = new IntroLevel();
    private static char[][] map;


    public LevelLoader(int level) {
        loadLevel(level);
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
        for(int i=0;i<height;i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        addEntity();
    }

    public void addEntity()
    {
        for(int y=0;y<height;y++)
        {
            for(int x=0;x<width;x++)
            {
                System.err.print(map[y][x]);
                switch (map[y][x]){
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
                    case 'f' :
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new F_Item(x, y, Sprite.powerup_flames.getFxImage()));
                        board.addBrick(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 's' :
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addItem(new S_Item(x, y, Sprite.powerup_speed.getFxImage()));
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
                    default:
                        board.addGrass(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                }
            }
            System.err.println();
        }
    }

}
