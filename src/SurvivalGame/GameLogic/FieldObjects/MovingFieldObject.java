package SurvivalGame.GameLogic.FieldObjects;
import SurvivalGame.GameLogic.Terrain;
import java.util.HashMap;

public abstract class MovingFieldObject extends FieldObject {
    private int direction;
    private HashMap<Terrain,Integer> moveSpeeds;
    public abstract int getMoveTime();
    private static Terrain[] moveableTerrains;
    public int getDirection(){
        return direction;
    };
}
