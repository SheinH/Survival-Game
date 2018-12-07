package SurvivalGame.GameLogic.FieldObjects;

public abstract class Carnivore extends MovingFieldObject implements Attacker, HealthObject{
    protected int health;
    protected int damage;

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int i) {
        health = i;
    }

    @Override
    public void attack(HealthObject target) {
        target.lowerHealth(damage);
    }
}
