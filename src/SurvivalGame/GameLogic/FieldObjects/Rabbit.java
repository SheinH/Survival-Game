package SurvivalGame.GameLogic.FieldObjects;

public class Rabbit extends Herbivore {

    public Rabbit(){
        super(10);
    }
    @Override
    public char getChar() {
        return 'R';
    }
}
