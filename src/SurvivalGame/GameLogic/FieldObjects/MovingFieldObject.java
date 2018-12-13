package SurvivalGame.GameLogic.FieldObjects;
import SurvivalGame.GameLogic.FieldObject;
import SurvivalGame.GameLogic.Point;
import SurvivalGame.GameLogic.Terrain;
import SurvivalGame.GameLogic.Tile;

import java.util.HashMap;
import java.util.Random;

public abstract class MovingFieldObject extends FieldObject {
    private Direction direction;
    private int moveTime;
    private HashMap<Terrain,Integer> moveSpeeds;
    public int getMoveTime(){
        return moveTime;
    };

    public MovingFieldObject() {
        direction = Direction.NONE;
        moveSpeeds = new HashMap<>();
        moveTime = 0;
    }

    public void setMoveSpeed(Terrain t, int ms){
        moveSpeeds.put(t,ms);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getMoveSpeed(){
        Terrain t = getTile().getTerrain();
        //return moveSpeeds.get(t);
        return 30;
    }

    @Override
    public void update() {
        if(moveTime > 0)
            moveTime--;
        else{
            System.out.println("MOVED");
            moveForward();
            changeDirection();
            moveTime += 2;
        }
    }

    public void moveForward() {
        if(direction != Direction.NONE) {
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
            moveTo(new Point(y,x));
        }
    }

    private void moveTo(Point dest){
        if(! getField().inBounds(dest) || getField().getTile(dest).hasObject()){
            setDirection(Direction.NONE);
        }
        else {
            Tile destTile = getField().getTile(dest);
            Tile currentTile = getTile();
            currentTile.getObjects().remove(this);
            destTile.getObjects().add(this);
            setPoint(dest);
            System.out.printf("Moved to %d, %d",dest.getY(), dest.getX());
        }
    }

    public void addMoveTime(int i){
        moveTime += i;
    }

    private static Terrain[] moveableTerrains;
    public Direction getDirection(){
        return direction;
    };
    public void changeDirection(){
        int rand = new Random().nextInt();
        if(direction == Direction.NONE){
            int rand2 = Math.floorMod(rand,4);
            switch(rand2){
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
        }
        else{
            Double random = Math.random();
            Direction turnLeft,turnRight;
            if(direction == Direction.UP || direction == Direction.DOWN){
                turnLeft = Direction.LEFT;
                turnRight = Direction.RIGHT;
            }
            else{
                turnLeft = Direction.UP;
                turnRight = Direction.DOWN;
            }
            if(random <= 0.5)
                return;
            else if(random <= 0.7)
                direction = turnLeft;
            else if (random <= 0.9)
                direction = turnRight;
            else
                direction = Direction.NONE;
        }
    }
}
