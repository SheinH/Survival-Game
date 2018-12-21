package SurvivalGame.GameLogic.FieldObjects;

public class Rabbit extends Herbivore {

    public Rabbit(){
        super(5);
    }
    @Override
    public char getChar() {
        return 'R';
    }
}
