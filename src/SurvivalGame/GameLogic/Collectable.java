package SurvivalGame.GameLogic;
import java.util.ArrayList;
import java.util.List;


public interface Collectable {
    int total = 20;
    List<String> items = new ArrayList<>();
    private int giveitems(int i){
        return i;
    }
    public void additems();

}

abstract class Tree implements Collectable {
    int total = 20;
    int giveitems(int items){
        return items;
    }
    public void addites(){
        for (int i = 0; i < total; i++){
            items.add("Sticks");
        }
    }
}

abstract class Bush implements Collectable {
    int total = 10;
    int giveitems(int items){
        return items;
    }
    public void addites() {
        for (int i = 0; i < total; i++) {
            items.add("Berries");
        }
    }
}

