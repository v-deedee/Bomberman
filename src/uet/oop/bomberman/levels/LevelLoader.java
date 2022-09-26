package uet.oop.bomberman.levels;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
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
    protected Board board;
    private static char map[][];

    public LevelLoader(Board board, int level) {
        loadLevel(level);
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
//        addEntity(_level);
    }

//    public void addEntity(int level)
//    {
//        for(int y=0;y<height;y++)
//        {
//            for(int x=0;x<width;x++)
//            {
//                switch (map[y][x]){
//                    case ' ':
//                    case '1':
//                    case '2':
//                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
//                        break;
//                    case '#':
//                        board.addEntity(new Wall(x, y, Sprite.wall.getFxImage()));
//                        break;
//                    case '*':
//                        board.addEntity(new Grass(x, y, Sprite.grass.getFxImage()));
//                        board.addEntity(new Brick(x, y, Sprite.brick.getFxImage()));
//                        break;
//                    case 'p':
//                        board.addBomber(new Bomber(x, y, Sprite.player_right.getFxImage()));
//                        break;
//                }
//            }
//        }
//    }

}
