package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.Items.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class ItemsList extends ArrayList<Item> {

    public ItemsList() {
        super();
        updaters = new ArrayList<>();
    }

    private ArrayList<Runnable> updaters;

    public void update(){
        for(Runnable r : updaters)
            r.run();
    }

    public void addUpdater(Runnable r){
        updaters.add(r);
    }

    public Item getItem(Class<? extends Item> type){
        for(Item i : this) {
            if (type.isInstance(i))
                return i;
        }
        return null;
    }

    @Override
    public void add(int index, Item item) {
        if(this.contains(item)) {
            Item content = get(indexOf(item));
            content.setQuantity(content.getQuantity() + item.getQuantity());
        }
        else
            super.add(index, item);
    }

    @Override
    public boolean add(Item item) {
        if(this.contains(item)) {
            Item content = get(indexOf(item));
            content.setQuantity(content.getQuantity() + item.getQuantity());
            return true;
        }
        else
            return super.add(item);
    }
}



/*
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import SurvivalGame.GameLogic.Items.*;

public class ItemsList {

    private List<Item> items;

    ArrayList<Runnable> updaters;

    public ItemsList(){
        items = new ArrayList<>(10);
        updaters = new ArrayList<>();
    }
    public <T extends Item> boolean isInBag(T find){
        final String className = find.getClass().getName();
        for(int i = 0, size = items.size(); (i < size) &&(items.get(i) != null); ++i) {
            if (items.get(i).getClass().getName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    private void update(){
        for(Runnable r : updaters)
            r.run();
    }

    public void addUpdater(Runnable r){
        updaters.add(r);
    }

    public boolean isInList(Class<? extends Item> type){
        for(Item i : items) {
            if (type.isInstance(i))
                if (i.getQuantity() > 0)
                    return true;
        }
        return false;
    }

    public Item getItem(Class<? extends Item> type){
        for(Item i : items) {
            if (type.isInstance(i))
                return i;
        }
        return null;
    }

    public Item removeItem(Class<? extends Item> type, int quantity){
        Item i = getItem(type);
        if(quantity > i.getQuantity()){
            items.remove(i);
            update();
            return i;
        }
        else{
            i.setQuantity(i.getQuantity() - quantity);
            Item ret = null;
            try {
                Constructor<? extends Item> constructor = type.getConstructor();
                ret = constructor.newInstance();
            }
            catch(Exception nse){
                System.out.println("ERROR BAD STUFF HAPPEN");
            }
            update();
            return ret;
        }
    }

    public<T extends Item> T getItem(List<Item> bag, T find) {
        T result = null;
        final String className = find.getClass().getName();
        for(int i = 0, size = bag.size(); (i < size) &&(bag.get(i) != null); ++i){
            if(bag.get(i).getClass().getName().equals(className)){
                result = (T) bag.get(i);
                break;
            }
        }
        return result;
    }


    public <T extends Item> void showAllItem(List<Item> bag){
        for(Item item: bag){
            System.out.println("Available Item(s) and quantity in items");
            System.out.printf("%s-%d%n");
        }

        for(int i = 0, size = bag.size(); (i < size) &&(bag.get(i) != null); ++i){

        }
    }

    public <T extends Item> void addItem(T item, int quantity){
        T bagItem = getItem(this.items, item);
        bagItem.changeQuantity(quantity);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item i){
        if(isInList(i.getClass())){
            Item target = getItem(i.getClass());
            target.setQuantity(target.getQuantity() + i.getQuantity());
        }
        update();
    }

    //remove object function



}*/