package SurvivalGame.GameLogic.FieldObjects;

public class Crocodile extends Carnivore{
    @Override
    public void attack(HealthObject target) {
        target.lowerHealth(5);
    }

    @Override
    public char getChar() {
        return 'C';
    }

    @Override
    public void destroy() {

    }

    @Override
    public int getMoveTime() {
        return 0;
    }
}
