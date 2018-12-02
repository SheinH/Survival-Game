package SurvivalGame.GameLogic.FieldObjects;

public class Tree extends FieldObject implements Collectable {
    int total = 20;

    int giveitems(int items) {
        return items;
    }

    public void addites() {
        for (int i = 0; i < total; i++) {
            items.add("Sticks");
        }
    }
}