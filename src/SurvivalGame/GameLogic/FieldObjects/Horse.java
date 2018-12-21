package SurvivalGame.GameLogic.FieldObjects;

public class Horse extends Herbivore{

    public Horse(){
        super(20);
    }
    @Override
    public char getChar() {
        return 'H';
    }

    @Override
    public int getMaxHealth(){return 0;}
}
