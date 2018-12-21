package SurvivalGame.GameLogic.Items;

import SurvivalGame.GameLogic.FieldObjects.HealthObject;

public class Torch extends Tool{
    private int health;

    private static final String NAME = "Torch";

    public Torch() {
        this(100);
    }

    public Torch(int health) {
        super(3, 15, 1);

        if(health <= 0){
            throw new IllegalArgumentException("Health must be > 0");
        }

        this.health = health;
    }

    public int getHealth(){return health;}

    //setHealth(int difference): return the newValue = oldValue + difference;

    public void setHealth(int difference){
        this.health = this.health + difference;
    }


    public String getName() {
        return NAME;
    }

    public int getMaxHealth() {return 0;}
}