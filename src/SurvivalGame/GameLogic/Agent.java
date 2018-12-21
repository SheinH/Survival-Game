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
    public int count = 0;

    private transient ItemsList list;
    private int equippedItemIndex;

    public Agent(int health){
        list = new ItemsList();
        this.health = health;
    }
    public Agent(){
        this.health = 100;
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
    public void addMoveTime() {
        if(getDirection() != Direction.NONE)
            moveTime += 2;
    }

    @Override
    public int getMoveTime() {
        return 1;
    }

}
