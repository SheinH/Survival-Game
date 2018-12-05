package SurvivalGame.GameLogic.FieldObjects;

public class Lion extends Carnivore{
    @Override
    public void attack(HealthObject target) {
        target.setHealth(target.getHealth() - damage);
    }

    @Override
    public int getMoveTime() {
        return 15;
    }
}
