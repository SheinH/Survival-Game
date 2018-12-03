package SurvivalGame.GameLogic;

public class Fence extends FieldObject implements Health{

    private int final MAX_LEVEL = 10;
    private int level = 0;
    private int health = 0;

    public class(Point point, ... , int level, int health) {
        //super(Point point, ...) not clear yet

    }

    //getter methods

    @Override
    public int getHealth() {return health;}

    public void getLevel() {return level;}

    //getter methods

    @Override
    public void setHealth(int difference) {
        //when health < 0, this will be destroyed => no need for exception
        if(this.health + difference < 0){
            health = 0
        } else{
            this.health = this.health + difference;
        }
    }

    public void setLevel(int difference) {
        if(this.level + difference < 0){
            throw new IllegalArgumentException("level must be >= 0");
        }

        this.level = this.level + difference;
    }

    @Override
    public void showHealth(){
        System.out.println("Fence's health is " + health);
    }



}