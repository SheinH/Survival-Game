package SurvivalGame.GameLogic.Items;

public class Spear extends Tool{
    private static final String NAME = "Spear";

    public Spear() {
        super(10, 50,3);
    }
    @Override
    public String getName() {
        return NAME;
    }

//        super(10, "Spear",50,3); }
}