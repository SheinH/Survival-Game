package SurvivalGame.GameLogic.FieldObjects;


import SurvivalGame.GameLogic.Point;
import java.util.List;

public interface Attacker {
    void attack(HealthObject target);


    public List<Point> attackArea();

//    public boolean isRock(List<Point> attackArea);
//
//    public boolean isRock(Point point);

    public boolean isTarget(List<Point> attackArea);

    public Point placeToAttack(List<Point> attackArea);

    public void goToTarget();

}
