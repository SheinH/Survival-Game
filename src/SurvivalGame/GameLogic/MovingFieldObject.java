package SurvivalGame.GameLogic;

public abstract class MovingFieldObject extends FieldObject {
    private int direction;
    private int[] moveSpeeds;
    public abstract int getMoveTime();
    private static Terrain[] moveableTerrains;
    public int getDirection(){
        return direction;
    };
}
