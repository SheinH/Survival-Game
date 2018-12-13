package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Attacker;
import SurvivalGame.GameLogic.FieldObjects.HealthObject;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

public class Agent extends MovingFieldObject implements Attacker, HealthObject {

    private final double FOOD_CAPACITY = 20;
    private final double ITEM_CAPACITY = 30;
    private int health ;

    private ItemsList list = new ItemsList();

    public Agent(int health){
        list = new ItemsList();
        this.health = health;
    }



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
    public void changeDirection() {

    }

    @Override
    public int getMoveTime() {
        return 1;
    }
}
