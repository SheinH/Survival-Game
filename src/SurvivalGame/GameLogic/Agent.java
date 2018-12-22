package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.Attacker;
import SurvivalGame.GameLogic.FieldObjects.Direction;
import SurvivalGame.GameLogic.FieldObjects.HealthObject;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;
import SurvivalGame.GameLogic.Items.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Agent extends MovingFieldObject implements Attacker, HealthObject {


    private final double FOOD_CAPACITY = 20;
    private final double ITEM_CAPACITY = 30;
    private final int MAX_HEALTH = 100 ;

    public int count = 0;

    private transient ItemsList list;
    private SimpleIntegerProperty equippedItemIndex;

    public Agent(int health){
        super(health);
        list = new ItemsList();
        equippedItemIndex = new SimpleIntegerProperty();
    }

    public Agent(){
        this(100);
    }

    public ItemsList getItemsList() {
        return list;
    }

    public Tool getEquippedTool(){
        return (Tool)list.get(equippedItemIndex.get());
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

    @Override
    public void update() {
        super.update();
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
            actionTime += getMoveSpeed();
    }

    @Override
    public void goToAgent(){return ;}

    public void makeIntegerProperty(){
        equippedItemIndex = new SimpleIntegerProperty();
    }
    public int getEquippedItemIndex() {
        return equippedItemIndex.get();
    }

    public SimpleIntegerProperty equippedItemIndexProperty() {
        return equippedItemIndex;
    }

    public void offsetItemIndex(int offset){
        int index = equippedItemIndex.get() + offset;
        index = Math.floorMod(index,getItemsList().size());
        equippedItemIndex.set(index);
    }
}
