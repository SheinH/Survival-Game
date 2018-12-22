package SurvivalGame.GameLogic.Items;

import javafx.beans.Observable;

import java.util.ArrayList;

public abstract class Item {

    private int quantity = 0;
    private double weight;


    public Item(double weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("weight must be >= 0");
        }
        this.weight = weight;
    }
//    public Item(double weight, String name) {
//        if(weight < 0) {
//            throw new IllegalArgumentException("weight must be >= 0");
//        }
//        this.weight = weight;
//    }

    //getter

    public double getWeight() {return this.weight; }

    public abstract String getName();

    public int getQuantity(){return this.quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity(int change){
        if(this.quantity + change < 0){
            throw new IllegalArgumentException("Cannot use more than the availability");
        }

        this.quantity = this.quantity + change;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }





    //setter
//    public void setweight(double difference) {
//        if(this.weight + difference < 0) {
//            throw new IllegalArgumentException("weight must be >= 0");
//        }
//        this.weight = this.weight + difference;
//    }
}
