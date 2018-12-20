package SurvivalGame.GameLogic.FieldObjects;

public class Lion extends Carnivore{
    public Lion(){
        super(40,20,3);
    }

    @Override
    public char getChar() {
        return 'L';
    }


}
