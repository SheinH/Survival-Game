package SurvivalGame.GameLogic.FieldObjects;
import SurvivalGame.GameLogic.Agent;
import SurvivalGame.GameLogic.FieldObject;
public class Tree extends FieldObject implements Collectable {
    int total = 20;

    int giveitems(int items) {
        return items;
    }

    public void giveItems(Agent a) {
        for (int i = 0; i < total; i++) {
            //items.add("Sticks");
        }
    }

    @Override
    public char getChar() {
        return 'T';
    }

    @Override
    public void update() {

    }
}