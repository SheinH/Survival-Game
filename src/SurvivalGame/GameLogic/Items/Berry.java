package SurvivalGame.GameLogic.Items;

public class Berry extends Food {

    private final String NAME = "Berry";

    public Berry() {
        super(0.1, 1);
    }

    public Berry(int quantity) {
        this();
        setQuantity(quantity);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
