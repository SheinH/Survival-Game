package SurvivalGame.GameLogic.FieldObjects;

public class Rabbit extends Herbivore {
    private int health;
    public Rabbit(){

    }

    @Override
    public int getMoveTime() {
        return 15;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int i) {
        health = i;
    }
}
