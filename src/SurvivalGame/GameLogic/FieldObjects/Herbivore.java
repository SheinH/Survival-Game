package SurvivalGame.GameLogic.FieldObjects;

public abstract class Herbivore extends MovingFieldObject implements HealthObject{
    protected int health;

    public Herbivore(int health){
        super(health);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int i) {
        health = i;
    }

    @Override
    public int getMaxHealth(){return 0;}
}
