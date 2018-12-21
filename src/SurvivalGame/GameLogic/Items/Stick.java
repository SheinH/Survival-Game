package SurvivalGame.GameLogic.Items;

public class Stick extends Tool {
    private static final String NAME = "Stick";
    public Stick() {
        super(1,10,1);
    }
    @Override
    public String getName() {
        return NAME;
    }
//    public Stick() {
//            super(1,"Stick",10,1);
//        }



}