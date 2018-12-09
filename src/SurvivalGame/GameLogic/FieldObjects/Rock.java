package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Agent;
import SurvivalGame.GameLogic.FieldObject;

public class Rock extends FieldObject implements Collectable {
    @Override
    public void update() {

    }

    @Override
    public void giveItems(Agent a) {

    }

    @Override
    public char getChar() {
        return 'R';
    }
}
