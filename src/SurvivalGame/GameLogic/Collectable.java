import java.util.ArrayList;
import java.util.List;

public interface Collectable {
    List<String> items = new ArrayList<>();
    private int giveitems(int i){
        return i;
    }

}

class Tree implements Collectable {
    int total = 20;
    int giveitems(int items){
        return items;
    }
}

class Bush implements Collectable {
    int total = 10;
    int giveitems(int items){
        return items;
    }
}

