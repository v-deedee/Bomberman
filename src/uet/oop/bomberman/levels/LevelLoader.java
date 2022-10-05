package uet.oop.bomberman.levels;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Balloon;
import uet.oop.bomberman.entities.Enemy.Oneal;
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
    protected int width = 20;
    protected int height = 15;
    protected int _level;
    public Board board = new Board();
    private static char map[][];

    public LevelLoader(int level) {
        loadLevel(level);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char getMap(int i, int j) {
        return map[i][j];
    }

    public void loadLevel(int level) {
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
        _level = Integer.parseInt(levelInfo[0]);
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
                    case ' ':
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                    case '#':
                        board.addEntity(new Wall(x, y, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        board.addEntity(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        board.addEntity(new Portal(x, y, Sprite.portal.getFxImage()));
                        board.addEntity(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'p':
                        board.addBomber(new Bomber(x, y, Sprite.player_right.getFxImage()));
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                    case '1':
                        board.addEnemy(new Balloon(x, y, Sprite.balloom_right1.getFxImage()));
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                    case '2':
                        board.addEnemy(new Oneal(x, y, Sprite.oneal_right1.getFxImage()));
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                    default:
                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                }
            }
            System.err.println();
        }
    }

}