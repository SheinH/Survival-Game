package SurvivalGame.GameLogic.Items;

import java.util.ArrayList;

public class Item {

    private double weight;


    public Item(){ this(0); }

    public Item(double weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("weight must be >= 0");
        }
        this.weight = weight;
    }

    //getter

    public double getweight() {return this.weight; }

    //public String getName(){ return this.name; }

    //setter
    public void setweight(double difference) {
        if(this.weight + difference < 0) {
            throw new IllegalArgumentException("weight must be >= 0");
        }
        this.weight = this.weight + difference;
    }
}
