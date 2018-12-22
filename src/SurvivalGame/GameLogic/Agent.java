package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Attacker;
import SurvivalGame.GameLogic.FieldObjects.Direction;
import SurvivalGame.GameLogic.FieldObjects.HealthObject;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;
import SurvivalGame.GameLogic.Items.*;
import SurvivalGame.GameLogic.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent extends MovingFieldObject implements Attacker, HealthObject {


    private final double FOOD_CAPACITY = 20;
    private final double ITEM_CAPACITY = 30;
    private final int MAX_HEALTH = 100 ;

    public int count = 0;

    private transient ItemsList list;
    private int equippedItemIndex;
    private List<Runnable> observers;

    public Agent(int health){
        super(health);
        list = new ItemsList();
        observers = new ArrayList<>();
    }
    public Agent(){
        this(100);
    }

    public ItemsList getItemsList() {
        return list;
    }

    public Tool getEquippedTool(){
        return (Tool)list.get(equippedItemIndex);
    }
    @Override
    public void attack(MovingFieldObject object) {
        if(object instanceof Agent){
            object.lowerHealth(50); // this will be modified later
        } else {
            return;
        }

    }

    @Override
    public int getMaxHealth(){return MAX_HEALTH;}

    public void addObserver(Runnable r){
        observers.add(r);
    }
    @Override
    public void update() {
        super.update();
    }

    public void updateItems(){
        observers.forEach(r -> r.run());
    }

    @Override
    public char getChar() {
        return 'A';
    }

    @Override
    public void changeDirection() {

    }
    @Override
    public ItemsList getLoot() {
        return null;
    }
    public void addMoveTime() {
        if(getDirection() != Direction.NONE)
            moveTime += getMoveSpeed();
    }

    @Override
    public int getMoveTime() {
        return 1;
    }

    @Override
    public void goToAgent(){return ;}

}
