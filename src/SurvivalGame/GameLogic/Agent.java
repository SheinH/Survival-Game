package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Attacker;
import SurvivalGame.GameLogic.FieldObjects.HealthObject;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

public class Agent extends MovingFieldObject implements Attacker, HealthObject {
    @Override
    public void attack(HealthObject target) {

    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int i) {

    }

    @Override
    public char getChar() {
        return 'A';
    }

    @Override
    public int getMoveTime() {
        return 0;
    }
}
