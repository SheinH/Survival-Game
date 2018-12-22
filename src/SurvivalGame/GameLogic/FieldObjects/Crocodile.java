package SurvivalGame.GameLogic.FieldObjects;

public class Crocodile extends Carnivore{

    public Crocodile(){
        super(45,20, 3);
    }

//    @Override
//    public void attack(HealthObject target) {
//        target.lowerHealth(5);
//    }

    @Override
    public char getChar() {
        return 'C';
    }

    @Override
    public void destroy() {

    }

    @Override
    public int getActionTime() {
        return 0;
    }

    @Override
    public int getMaxHealth(){return 0;}
}
