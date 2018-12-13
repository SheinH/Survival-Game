package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Terrain;

public class Bear extends Carnivore{
    public Bear() {
    }

    @Override
    public char getChar() {
        return 'B';
    }

    @Override
    public void destroy() {

    }

    @Override
    public void attack(HealthObject target) {

    }
}
