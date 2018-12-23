package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.FieldObject;
import SurvivalGame.GameLogic.Point;
import SurvivalGame.GameLogic.Terrain;
import SurvivalGame.GameLogic.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class MovingFieldObject extends FieldObject implements HealthObject {
    private Direction direction;
    private int health;
    private int maxHealth;
    protected int actionTime;
    private HashMap<Terrain, Integer> moveSpeeds;
    private List<Terrain> movableTerrains;


    public int getActionTime() {
        return actionTime;
    }


    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public MovingFieldObject(int health) {
        direction = Direction.NONE;
        moveSpeeds = new HashMap<>();
        actionTime = 0;
        movableTerrains = new ArrayList<Terrain>();
        if (health <= 0) {
            throw new IllegalArgumentException("health must be > 0");
        }
        this.health = health;
    }

    public void setMoveableTerrain(Terrain t, boolean b) {
        if (movableTerrains.contains(t) && !b)
            movableTerrains.remove(t);
        else if (!movableTerrains.contains(t) && b)
            movableTerrains.add(t);
    }

    public void setMoveSpeed(Terrain t, int ms) {
        moveSpeeds.put(t, ms);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getMoveSpeed() {
        Terrain t = getTile().getTerrain();
        //return moveSpeeds.get(t);
        return moveSpeeds.get(t);
    }

    @Override
    public void update() {
        if (actionTime > 0)
            actionTime--;
        else {
            moveForward();
            changeDirection();
            addMoveTime();
        }
    }

    public void moveForward() {
        if (direction != Direction.NONE) {
            int x = getPoint().getX();
            int y = getPoint().getY();
            switch (direction) {
                case UP:
                    y -= 1;
                    break;
                case DOWN:
                    y += 1;
                    break;
                case LEFT:
                    x -= 1;
                    break;
                case RIGHT:
                    x += 1;
                    break;
            }
            moveTo(new Point(y, x));
        }
    }

    public void face(Point p){
        Point offset = getPoint().offsetTo(p);
        int dx = offset.getX();
        int dy = offset.getY();

        if(Math.abs(dx) > Math.abs(dy) || (Math.abs(dx) == Math.abs(dy) && Math.random() > 0.5) ){
            if(dx > 0)
                setDirection(Direction.RIGHT);
            else if(dx < 0)
                setDirection(Direction.LEFT);
        }
        else{
            if(dy > 0)
                setDirection(Direction.DOWN);
            else if(dy < 0)
                setDirection(Direction.UP);
        }
    }

    public boolean canMoveTo(Point dest){
        Tile t = getField().getTile(dest);
        return !t.hasObject() && movableTerrains.contains(t.getTerrain());
    }
    public boolean moveTo(Point dest) {
        if (!getField().inBounds(dest) || !canMoveTo(dest)) {
            setDirection(Direction.NONE);
            return false;
        }
        else {
            Tile destTile = getField().getTile(dest);
            Tile currentTile = getTile();
            currentTile.getObjects().remove(this);
            destTile.getObjects().add(this);
            setPoint(dest);                             //where did you get did function
            return true;
        }
    }

    protected void addMoveTime(int i) {
        actionTime += i;
    }

    public void addMoveTime() {
        actionTime += getMoveSpeed();
    }

    public void reduceMoveTime() {
        actionTime -= 1;}

    public Direction getDirection() {
        return direction;
    }



    public void changeDirection() {
        int rand = new Random().nextInt();
        if (direction == Direction.NONE) {
            int rand2 = Math.floorMod(rand, 4);
            switch (rand2) {
                case 0:
                    direction = Direction.UP;
                    break;
                case 1:
                    direction = Direction.DOWN;
                    break;
                case 2:
                    direction = Direction.LEFT;
                    break;
                default:
                    direction = Direction.RIGHT;
                    break;
            }
        } else {
            Double random = Math.random();
            Direction turnLeft, turnRight;
            if (direction == Direction.UP || direction == Direction.DOWN) {
                turnLeft = Direction.LEFT;
                turnRight = Direction.RIGHT;
            } else {
                turnLeft = Direction.UP;
                turnRight = Direction.DOWN;
            }
            if (random <= 0.5)
                return;
            else if (random <= 0.7)
                direction = turnLeft;
            else if (random <= 0.9)
                direction = turnRight;
            else
                direction = Direction.NONE;
        }
    }

    public abstract void goToAgent();    //test added 5:15AM

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("health must be > 0");
        }

        this.health = health;
    }
}
