package SurvivalGame.GameLogic.FieldObjects;
import SurvivalGame.GameLogic.FieldObject;
import SurvivalGame.GameLogic.Terrain;
import java.util.HashMap;
import java.util.Random;

public abstract class MovingFieldObject extends FieldObject {
    public enum Direction{
        NONE,UP,DOWN,LEFT,RIGHT;
    }
    private Direction direction;
    protected HashMap<Terrain,Integer> moveSpeeds;
    public abstract int getMoveTime();
    private static Terrain[] moveableTerrains;
    public Direction getDirection(){
        return direction;
    };
    public void changeDirectionRandomly(){
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
