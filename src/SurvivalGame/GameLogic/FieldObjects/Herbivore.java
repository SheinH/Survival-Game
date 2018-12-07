package SurvivalGame.GameLogic.FieldObjects;

public abstract class Herbivore extends MovingFieldObject implements HealthObject{
    protected int health;

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int i) {
        health = i;
    }
}
