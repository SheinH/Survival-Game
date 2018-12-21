package SurvivalGame.GameLogic.Items;

public class Stone extends Tool{
    private final String NAME = "Stone";

    public Stone() {
        super(2,30,2);
    }

    @Override
    public String getName() {
        return NAME;
    }
//    public Stone() {
//        super(2, "Stone",30,2);
//    }

}