package SurvivalGame.GameLogic;

import java.util.stream.*;
import java.util.function.*;
import java.util.ArrayList;
import java.util.List;
import SurvivalGame.GameLogic.Items.*;

public class ItemsList {

    List<Item> bag;

    public ItemsList(){
        bag = new ArrayList<>(10);
        bag.add(new Hand());
    }

    public <T extends Item> T getItem(T findItem){
        T result = (T) bag.stream()
                          .filter(item->item.getName().equals(findItem.getName()) )
                          .findFirst()
                          .get();
        return result;
    }

    //remove object function


//    //getter methods
//
//    public int getStick(){
//        int result = (int) bag.stream()
//                              .filter(item-> item.getName().equals("stick"))
//                              .count();
//
//        return result;
//    }
//
//
//    public int getTorch(){
//        int result = (int) bag.stream()
//                .filter(item-> item.getName().equals("torch"))
//                .count();
//
//        return result;
//    }
//
//    public  int getStone(){
//        int result = (int) bag.stream()
//                .filter(item-> item.getName().equals("stone"))
//                .count();
//
//        return result;
//    }
//
//    public int getSpear(){
//        int result = (int) bag.stream()
//                .filter(item-> item.getName().equals("spear"))
//                .count();
//
//        return result;
//    }
//
//    public int getBerry(){
//        int result = (int) bag.stream()
//                .filter(item-> item.getName().equals("berry"))
//                .count();
//
//        return result;
//    }
//
//    public int getMeat(){
//        int result = (int) bag.stream()
//                .filter(item-> item.getName().equals("meat"))
//                .count();
//
//        return result;
//    }
//
//
//    //this function is for agent implementaion only
//    boolean isFull(){
//        double currentWeight =  1 * getStick() + 3 * getTorch() + 2 * getStone() + 10 * getSpear() + 0.1 * getBerry() +
//                                1 * getMeat();
//        if(currentWeight > 100){
//            System.out.print("You cannot collect more thant you can carry");
//            return true;
//        } else {
//            return false;
//        }
//}
//
//    //setter Methods()
//    // difference can be negative or positive
//    // positive when agent picks, catches or makes
//    // negative when agent uses items
//
//    public void addStick (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Stick());
//        }
//    }
//
//    public void removeStick(int removeValue){
//            if(removeValue > this.getStick()){
//                System.out.println("Maximum available value is " + this.getStick());
//            } else{
//                for(int i = 0; i < removeValue; ++i){
//
//                    bag.remove(Stick.getName().equals("Stick"));
//                }
//            }
//
//    }
//
//    public void addTorch (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Torch());
//        }
//    }
//
//    public void removeTorch(int removeValue){
//        if(removeValue > this.getTorch()){
//            System.out.println("Maximum available value is " + this.getTorch());
//        } else{
//            for(int i = 0; i < removeValue; ++i){
//
//                bag.remove(Torch.getName().equals("Torch"));
//            }
//        }
//
//    }
//
//    public void addStone (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Stone());
//        }
//    }
//
//    public void removeStone(int removeValue){
//        if(removeValue > this.getStone()){
//            System.out.println("Maximum available value is " + this.getStone());
//        } else{
//            for(int i = 0; i < removeValue; ++i){
//
//                bag.remove(Stone.getName().equals("Stone"));
//            }
//        }
//
//    }
//
//
//
//    public void addSpear (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Spear());
//        }
//    }
//
//    public void removeSpear(int removeValue){
//        if(removeValue > this.getSpear()){
//            System.out.println("Maximum available value is " + this.getSpear());
//        } else{
//            for(int i = 0; i < removeValue; ++i){
//
//                bag.remove(Spear.getName().equals("Spear"));
//            }
//        }
//
//    }
//
//    public void addBerry (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Berry());
//        }
//    }
//
//    public void removeBerry(int removeValue){
//        if(removeValue > this.getBerry()){
//            System.out.println("Maximum available value is " + this.getBerry());
//        } else{
//            for(int i = 0; i < removeValue; ++i){
//
//                bag.remove(Berry.getName().equals("Berry"));
//            }
//        }
//
//    }
//
//    public void addMeat (int addValue) {
//        for(int i = 0; i < addValue; ++i){
//            bag.add(new Meat());
//        }
//    }
//
//    public void removeMeat(int removeValue){
//        if(removeValue > this.getMeat()){
//            System.out.println("Maximum available value is " + this.getMeat());
//        } else{
//            for(int i = 0; i < removeValue; ++i){
//
//                bag.remove(Meat.getName().equals("Meat"));
//            }
//        }
//
//    }

}