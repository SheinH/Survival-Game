package SurvivalGame.GameLogic.Items;

public abstract class Food extends Item {
    private int health;
    private final String NAME = "Food";

    public Food(double weight, int health){
        super(weight);

        if(health <= 0){
            throw new IllegalArgumentException("Health must be > 0");
        }

        this.health = health;
    }

    //getHealth(): to show the health that agent can get when eating this food
    public int getHealth() { return health; }

//    @Override
//    public String getName() {return this.NAME;}
}
