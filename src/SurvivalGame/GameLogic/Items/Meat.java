package SurvivalGame.GameLogic.Items;

public class Meat extends Food {
    private final String NAME = "Meat";
    public Meat() {
        super(1, 5);
    }

    @Override
    public String getName() {
        return NAME;
    }

    //    public Meat() {
//        super(1,"Meat", 5);
//    }
}
