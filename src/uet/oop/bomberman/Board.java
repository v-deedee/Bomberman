package uet.oop.bomberman;

import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Entity> entities = new ArrayList<>();
    public List<Bomber> bombers = new ArrayList<>();

    public List<Enemy> enemies = new ArrayList<>();

    public Board()
    {
    }
    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }
    public void addBomber(Bomber bomber)
    {
        bombers.add(bomber);
    }
    public void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }
}
