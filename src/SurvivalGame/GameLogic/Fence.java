package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.HealthObject;

public class Fence extends FieldObject implements HealthObject {

    //TODO fix this
    private final int MAX_LEVEL = 10;
    private int level = 0;
    private int health = 0;

//    public class(Point point, ... , int level, int health) {
//        //super(Point point, ...) not clear yet
//
//    }

    //getter methods

    @Override
    public int getHealth() {return health;}

    public int getLevel() {return level;}

    //getter methods

    @Override
    public void setHealth(int difference) {
        //when health < 0, this will be destroyed => no need for exception
        if(this.health + difference < 0){
            health = 0;
        } else{
            this.health = this.health + difference;
        }
    }

    public void setLevel(int difference) {
        if(this.level + difference < 0){
            throw new IllegalArgumentException("level must be >= 0");
        }

        this.level = this.level + difference;
    }

    public void showHealth(){
        System.out.println("Fence's health is " + health);
    }



}