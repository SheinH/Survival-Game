package SurvivalGame.GameLogic.FieldObjects;

public class Crocodile extends Carnivore{
    @Override
    public void attack(HealthObject target) {
        target.lowerHealth(5);
    }
    publi
}
