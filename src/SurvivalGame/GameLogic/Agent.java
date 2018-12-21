package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Attacker;
import SurvivalGame.GameLogic.FieldObjects.Direction;
import SurvivalGame.GameLogic.FieldObjects.HealthObject;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;
import SurvivalGame.GameLogic.Items.*;
import SurvivalGame.GameLogic.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class Agent extends MovingFieldObject implements Attacker, HealthObject {


    private final double FOOD_CAPACITY = 20;
    private final double ITEM_CAPACITY = 30;
    private int health ;
    private final int MAX_HEALTH = 100 ;

    public int count = 0;

    private transient ItemsList list;
    private int equippedItemIndex;

    public Agent(int health){
        super(health);
        list = new ItemsList();

    }
    public Agent(){
        super(100);

        list = new ItemsList();
    }

    public ItemsList getItemsList() {
        return list;
    }

    public Tool getEquippedTool(){
        return (Tool)list.get(equippedItemIndex);
    }
    @Override
    public void attack(HealthObject target) {

    }

    @Override
    public int getMaxHealth(){return MAX_HEALTH;}

    @Override
    public int getHealth() { return health;}

    @Override
    public void setHealth(int health) {
        if(health <= 0) {
            throw new IllegalArgumentException("health must be > 0");
        }
        this.health = health;
    }

    @Override
    public char getChar() {
        return 'A';
    }

    @Override
    public void changeDirection() {

    }

    @Override
    public void addMoveTime() {
        if(getDirection() != Direction.NONE)
            moveTime += 2;
    }

    @Override
    public int getMoveTime() {
        return 1;
    }

}
