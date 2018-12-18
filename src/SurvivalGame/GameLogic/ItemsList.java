package SurvivalGame.GameLogic;

import java.util.stream.*;
import java.util.function.*;
import java.util.ArrayList;
import java.util.List;
import SurvivalGame.GameLogic.Items.*;

public class ItemsList {

    private List<Item> bag;

    public ItemsList(){
        bag = new ArrayList<>(10);
    }

    public <T extends Item> boolean isInBag(List<Item> bag, T find){
        final String className = find.getClass().getName();
        for(int i = 0, size = bag.size(); (i < size) &&(bag.get(i) != null); ++i) {
            if (bag.get(i).getClass().getName().equals(className)) {
                return true;
            }
        }
        return false;
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
            System.out.println("Available Item(s) and quantity in bag");
            System.out.printf("%s-%d%n");
        }

        for(int i = 0, size = bag.size(); (i < size) &&(bag.get(i) != null); ++i){

        }
    }

    public <T extends Item> void addItem(T item, int quantity){
        T bagItem = getItem(this.bag, item);
        bagItem.changeQuantity(quantity);
    }

    //remove object function



}