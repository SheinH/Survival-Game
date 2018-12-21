package SurvivalGame.GameLogic.FieldObjects;

public abstract class Herbivore extends MovingFieldObject implements HealthObject{


    public Herbivore(int health){
        super(health);
    }

    @Override
    public int getMaxHealth(){return 0;}
}
