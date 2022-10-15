package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.Flame;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Portal;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.levels.LevelLoader;


import java.awt.*;
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

    public List<Item> items = new ArrayList<>();

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

    public void addItem(Item item) {
        items.add(item);
    }

    public void setEnemiesMovement(LevelLoader lvLoad) {
        for (Enemy e : enemies) {
            e.move(lvLoad);
        }
    }

    public void updateAllEntity(LevelLoader lvLoad) {
        bricks.forEach(Entity::update);
        grasses.forEach(Entity::update);
        portals.forEach(Entity::update);
        walls.forEach(Entity::update);
        bombers.forEach(Entity::update);
        bombs.forEach(Entity::update);
        flames.forEach(Entity::update);
        flameSegments.forEach(Entity::update);
        for (Enemy enemy : enemies) {
            enemy.update();
            collisionKillPlayerDetect(enemy.getX(), enemy.getY());
        }
        setEnemiesMovement(lvLoad);
        bombExplodeUpdate(lvLoad);

        if (!bombers.isEmpty()) {
            eatItemDetect(bombers.get(0).getX(), bombers.get(0).getY());
        }

        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).isRemoved) {
                flames.remove(i);
                i--;
            }
        }
        for (int i = 0; i < flameSegments.size(); i++) {
            if (flameSegments.get(i).isRemoved) {
                flameSegments.remove(i);
                i--;
            }
        }
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).isRemoved) {
                bricks.remove(i);
                i--;
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isRemoved) {
                items.remove(i);
                i--;
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isRemoved) {
                enemies.remove(i);
                i--;
            }
        }
    }

    public void renderAllEntity(GraphicsContext gc) {
        // variable distance to use Entity::render method
        double distance = bombers.get(0).getX() - LevelLoader.getWidth() * Sprite.SCALED_SIZE / 4.0;
        if (distance < 0) {
            distance = 0;
        }
        if (distance > LevelLoader.getWidth() * Sprite.SCALED_SIZE / 2.0) {
            distance = LevelLoader.getWidth() * Sprite.SCALED_SIZE / 2.0;
        }
        double finalDistance = distance;
        grasses.forEach(g -> g.render(gc, finalDistance));
        items.forEach(g -> g.render(gc, finalDistance));
        portals.forEach(g -> g.render(gc, finalDistance));
        walls.forEach(g -> g.render(gc, finalDistance));
        bricks.forEach(g -> g.render(gc, finalDistance));
        flames.forEach(g -> g.render(gc, finalDistance));
        flameSegments.forEach(g -> g.render(gc, finalDistance));
        enemies.forEach(g -> g.render(gc, finalDistance));
        bombs.forEach(g -> g.render(gc, finalDistance));
        for (Bomber bomber : bombers) {
            if (!bomber.isRemoved) {
                bomber.render(gc, finalDistance);
            }
        }
    }

    public void bombExplodeUpdate(LevelLoader lvLoad) {
        for (int i = 0; i < bombs.size(); i++) {
            double posX = bombs.get(i).getX() / Sprite.SCALED_SIZE;
            double posY = bombs.get(i).getY() / Sprite.SCALED_SIZE;
            if (!checkBomberWithBombs(bombs.get(i).getX(), bombs.get(i).getY())) {
                lvLoad.setMap((int) posY, (int) posX, '#');
            }
            if (bombs.get(i).isRemoved) {
                lvLoad.setMap((int) posY, (int) posX, ' ');
                flames.add(new Flame(posX, posY, Sprite.bomb_exploded.getFxImage()));
                killEnemyDetect(posX * Sprite.SCALED_SIZE, posY * Sprite.SCALED_SIZE);
                collisionKillPlayerDetect(posX * Sprite.SCALED_SIZE, posY * Sprite.SCALED_SIZE);
                for (int _direction = 0; _direction < 4; _direction++) {
                    boolean checkWallEnd = false; // check wall end flame segment
                    boolean checkAnotherBomb = false;
                    for (int j = 0; j < Bomber.bombRadius; j++) {
                        boolean canCreateFlameSeg = true;
                        boolean _last = false;
                        int segmentX = (int) posX;
                        int segmentY = (int) posY;
                        int diff = j + 1;
                        switch (_direction) {
                            case 0:
                                segmentY -= diff;
                                break;
                            case 1:
                                segmentX += diff;
                                break;
                            case 2:
                                segmentY += diff;
                                break;
                            case 3:
                                segmentX -= diff;
                                break;
                        }
                        char test = lvLoad.getMap(segmentY, segmentX);
                        for (Brick brick : bricks) {
                            if (brick.getX() / Sprite.SCALED_SIZE == segmentX
                                    && brick.getY() / Sprite.SCALED_SIZE == segmentY) {
                                brick.isExploded = true;
                                if (test != 'x') lvLoad.setMap(segmentY, segmentX, ' ');
                            }
                        }
                        for (Bomb bomb : bombs) {
                            if (bomb.getX() / Sprite.SCALED_SIZE == segmentX
                                    && bomb.getY() / Sprite.SCALED_SIZE == segmentY) {
                                bomb.isRemoved = true;
                                checkAnotherBomb = true;
                                canCreateFlameSeg = false;
                                flames.add(new Flame(segmentX, segmentY, Sprite.bomb_exploded.getFxImage()));
                                killEnemyDetect(segmentX * Sprite.SCALED_SIZE, segmentY * Sprite.SCALED_SIZE);
                                collisionKillPlayerDetect(segmentX * Sprite.SCALED_SIZE, segmentY * Sprite.SCALED_SIZE);
                            }
                        }
                        for (FlameSegment flameSegment : flameSegments) {
                            if (flameSegment.getX() / Sprite.SCALED_SIZE == segmentX
                                    && flameSegment.getY() / Sprite.SCALED_SIZE == segmentY) {
                                canCreateFlameSeg = false;
                                break;
                            }
                        }
                        if (j == Bomber.bombRadius - 1 && !checkAnotherBomb) _last = true;
                        if (test != '#' && test != '*' && test != 'x' && canCreateFlameSeg) {
                            flameSegments.add(new FlameSegment(segmentX, segmentY, _direction, _last));
                            killEnemyDetect(segmentX * Sprite.SCALED_SIZE, segmentY * Sprite.SCALED_SIZE);
                            collisionKillPlayerDetect(segmentX * Sprite.SCALED_SIZE, segmentY * Sprite.SCALED_SIZE);
                        } else checkWallEnd = true;
                        if (checkWallEnd) break;
                    }
                }
                bombs.remove(i);
                i--;
            }
        }
    }

    public void eatItemDetect(double x, double y) {
        double x1 = x + Sprite.SCALED_SIZE;
        double y1 = y + Sprite.SCALED_SIZE;
        for (Item item : items) {
            double x2 = item.getX() + Sprite.SCALED_SIZE / 2.0;
            double y2 = item.getY() + Sprite.SCALED_SIZE / 2.0;
            if (x2 >= x && x2 <= x1 && y2 >= y && y2 <= y1) {
                item.eaten();
                item.isRemoved = true;
            }
        }
    }

    public void killEnemyDetect(double x, double y) {
        for (Enemy enemy : enemies) {
            double topLeftX = enemy.getX();
            double topLeftY = enemy.getY();
            double downRightX = enemy.getX() + Sprite.SCALED_SIZE;
            double downRightY = enemy.getY() + Sprite.SCALED_SIZE;
            double t = Sprite.SCALED_SIZE;
            if ((topLeftX >= x && topLeftX <= x + t && topLeftY >= y && topLeftY <= y + t)
                    || (downRightX >= x && downRightX <= x + t && downRightY >= y && downRightY <= y + t)) {
                enemy.isExploded = true;
            }
        }
    }

    public boolean checkBomberWithBombs(double x, double y) {
        double topLeftX = bombers.get(0).getX();
        double topLeftY = bombers.get(0).getY();
        int t = Sprite.SCALED_SIZE;
        Rectangle bomber = new Rectangle((int) topLeftX, (int) topLeftY, t - 8, t);
        Rectangle bomb = new Rectangle((int) x, (int) y, t, t);
        return bomber.getBounds().intersects(bomb.getBounds());
    }

    public void collisionKillPlayerDetect(double x, double y) {
        for (Bomber bomber : bombers) {
            double topLeftX = bomber.getX();
            double topLeftY = bomber.getY();
            double downRightX = bomber.getX() + Sprite.SCALED_SIZE - 3;// fix hitbox
            double downRightY = bomber.getY() + Sprite.SCALED_SIZE - 3;
            double t = Sprite.SCALED_SIZE;
            if ((topLeftX >= x && topLeftX <= x + t && topLeftY >= y && topLeftY <= y + t)
                    || (downRightX >= x && downRightX <= x + t && downRightY >= y && downRightY <= y + t)) {
                bomber.isDead = true;
            }
        }
    }
}
