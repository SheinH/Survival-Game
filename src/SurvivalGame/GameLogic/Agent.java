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
    private List<Point> path;

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
        if (actionTime > 0)
            actionTime--;
        else {
            if(path != null && !path.isEmpty()){
                boolean b = moveTo(path.get(0));
                if(b) {
                    path.remove(0);
                    addMoveTime();
                }
                else
                    path.clear();
            }
            else {
                moveForward();
                if(getDirection() != Direction.NONE)
                    addMoveTime();
            }
        }
    }

    public void setDestination(Point destination){
        path = getField().findPath(this,destination);
    }

    public void clearPath(){
        path = null;
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
