package SurvivalGame.GameLogic;

public class Tool{
    private int damage;
    private int range;
    private int final weight;
    private final String name;

    public Tool(int damage, int range, String name){
        if(damage < 0){
            throw new IllegalArgumentException("damage must be > 0");
        }

        if(range < 0){
            throw new IllegalArgumentException("range must be > 0");
        }

        this.damage = damage;
        this.range = range;
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
    public void setDamage(int damage) {
        if(damage < 0){
            throw new IllegalArgumentException("damage must be > 0");
        }
        this.damage = damage;
    }

    //setRange()
    public void setRange(int range){
        if(range < 0){
            throw new IllegalArgumentException("range must be > 0");
        }
        this.range = range;
    }

}