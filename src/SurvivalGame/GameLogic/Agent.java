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
    Runnable onItemChange;
    public int count = 0;
    private ArrayList<Item> currentItemList = new ArrayList<>(Arrays.asList(new Hand(), new Stone(), new Spear(), new Torch(), new Berry(), new Meat(), new Stick()));

    private ItemsList itemsList = new ItemsList();

    private Item equippedItem = new Hand();

    public Agent(int health){
        itemsList = new ItemsList();
        this.health = health;
    }
    public Agent(){
        this.health = 100;
        itemsList = new ItemsList();
    }

    public Runnable getOnItemChange() {
        return onItemChange;
    }

    public void setOnItemChange(Runnable onItemChange) {
        this.onItemChange = onItemChange;
    }


    public void changeequippedItem(){
        if (count < 7){
        equippedItem = currentItemList.get(count);
        count++;}
        else{
            count = 0;
            equippedItem = currentItemList.get(count);
        }
        onItemChange.run();
    }

    public ItemsList getItemsList() {
        return itemsList;
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

    public Item getEquippedItem() {
        return equippedItem;
    }
}
