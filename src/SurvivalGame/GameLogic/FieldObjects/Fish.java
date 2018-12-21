package SurvivalGame.GameLogic.FieldObjects;

public class Fish extends Herbivore{

    public Fish(){
        super(5);
    }

    @Override
    public char getChar() {
        return 'H';
    }
}
