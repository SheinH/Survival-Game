package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Terrain;

public class Bear extends Carnivore{
    public Bear() {
        super(50, 17,3);
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
    @Override
    public int getMaxHealth(){return 0;}
}
