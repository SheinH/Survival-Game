package SurvivalGame.GameLogic;

import java.util.stream.*;
import java.util.function.*;
import java.util.ArrayList;
import java.util.List;
import SurvivalGame.GameLogic.Items.*;

public abstract class Capacity {

    List<Item> bag = new ArrayList<>(7);

    public Capacity(){
        bag.add(new Hand());
    }


    //getter methods

    public int getStick(){
        int result = (int) bag.stream()
                              .filter(Stick-> bag.contains(Stick))
                              .count();

        return result;
    }


    public int getTorch(){
        int result = (int) bag.stream()
                .filter(Torch-> bag.contains(Torch))
                .count();

        return result;
    }

    public  int getStone(){
        int result = (int) bag.stream()
                .filter(Stone-> bag.contains(Stone))
                .count();

        return result;
    }

    public int getSpear(){
        int result = (int) bag.stream()
                .filter(Spear-> bag.contains(Spear))
                .count();

        return result;
    }

    public int getBerry(){
        int result = (int) bag.stream()
                .filter(Berry-> bag.contains(Berry))
                .count();

        return result;
    }

    public int getMeat(){
        int result = (int) bag.stream()
                .filter(Meat-> bag.contains(Meat))
                .count();

        return result;
    }


    //this function is for agent implementaion only
    boolean isFull(){
        double currentWeight =  1 * getStick() + 3 * getTorch() + 2 * getStone() + 10 * getSpear() + 0.1 * getBerry() +
                                1 * getMeat();
        if(currentWeight > 100){
            System.out.print("You cannot collect more thant you can carry");
            return true;
        } else {
            return false;
        }
}

    //setter Methods()
    // difference can be negative or positive
    // positive when agent picks, catches or makes
    // negative when agent uses items

    public void setStick (int difference) {
        if(difference >= 0){
            if(!isFull()){

            }

        }
    }

    public abstract void setTorch (int difference);

    public abstract void setStone (int difference);

    public abstract void setSpear (int difference);

    public abstract void setBerry (int difference);

    public abstract void setMeat (int difference);

}