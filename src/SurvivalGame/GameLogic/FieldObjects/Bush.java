package SurvivalGame.GameLogic.FieldObjects;
import SurvivalGame.GameLogic.*;

public class Bush extends FieldObject implements Collectable {
    int total = 10;
    int giveitems(int items){
        return items;
    }
    public void giveItems(Agent a) {
        for (int i = 0; i < total; i++) {
            //items.add("Berries");
        }
    }
}

