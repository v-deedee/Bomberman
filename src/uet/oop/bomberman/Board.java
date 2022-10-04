package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Portal;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Bomber> bombers = new ArrayList<>();

    public List<Enemy> enemies = new ArrayList<>();

    public List<Bomb> bombs = new ArrayList<>();

    public List<Grass> grasses = new ArrayList<>();

    public List<Wall> walls = new ArrayList<>();

    public List<Brick> bricks = new ArrayList<>();

    public List<Portal> portals = new ArrayList<>();

    public List<Flame> flames = new ArrayList<>();

    public List<FlameSegment> flameSegments = new ArrayList<>();

    public Board() {
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void addGrass(Grass grass) {
        grasses.add(grass);
    }

    public void addPortal(Portal portal) {
        portals.add(portal);
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void updateAllEntity() {
        bricks.forEach(Entity::update);
        grasses.forEach(Entity::update);
        portals.forEach(Entity::update);
        walls.forEach(Entity::update);
        bombers.forEach(Entity::update);
        enemies.forEach(Entity::update);
        bombs.forEach(Entity::update);
        flames.forEach(Entity::update);

        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isRemoved) {
                double posX = bombs.get(i).getX() / Sprite.SCALED_SIZE;
                double posY = bombs.get(i).getY() / Sprite.SCALED_SIZE;
                flames.add(new Flame(posX, posY, Sprite.bomb_exploded.getFxImage()));
                bombs.remove(i);
                i--;
            }
        }
        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).isRemoved) {
                flames.remove(i);
                i--;
            }
        }
    }

    public void renderAllEntity(GraphicsContext gc) {
        grasses.forEach(g -> g.render(gc));
        portals.forEach(g -> g.render(gc));
        walls.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        bombers.forEach(g -> g.render(gc));
    }
}
