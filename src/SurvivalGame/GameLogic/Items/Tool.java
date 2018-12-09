package SurvivalGame.GameLogic.Items;

import SurvivalGame.GameLogic.SurvivalGame;

public abstract class Tool extends Item{
    private final int damage;
    private final int range;

    public Tool( int damage, int range) {
        this(0, damage, range);
    }


    public Tool (double weight, int damage, int range ) {
        super(weight);

        if(damage <= 0){
            throw new IllegalArgumentException("damage must be >= 0");
        }

        if(range < 0){
            throw new IllegalArgumentException("range must be > 0");
        }



        this.damage = damage;
        this.range = range;

    }

    //*getter methods

    //getDamage()
    public int getDamage(){return damage;}

    //getRange()
    public int getRange(){return range;}

    //*setter methods

//    //setDamage()
//    public void setDamage(int difference) {
//        if(this.damage + difference <= 0){
//            throw new IllegalArgumentException("damage must be >= 0");
//        }
//
//        this.damage = this.damage + difference;
//    }
}