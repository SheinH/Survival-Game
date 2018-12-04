package SurvivalGame.GameLogic.Items;

import SurvivalGame.GameLogic.SurvivalGame;

public abstract class Tool{
    private final int damage;
    private final int range;
    private final int weight;
    private final String name;

    public Tool(int damage, int range, int weight, String name) {
        if(damage < 0){
            throw new IllegalArgumentException("damage must be > 0");
        }

        if(range < 0){
            throw new IllegalArgumentException("range must be > 0");
        }
        this.damage = damage;
        this.range = range;
        this.weight = weight;
        this.name = name;
    }

    //*getter methods

    //getDamage()
    public int getDamage(){return damage;}

    //getRange()
    public int getRange(){return range;}

    //getName()
    public String getName(){return name;}

    //getWeight()
    public int getWeight() {return weight;}

    //*setter methods

    //setDamage()

}