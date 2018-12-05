package SurvivalGame.GameLogic.Items;

public abstract class Item {
    private int weight;
    private int quantity;
    protected String name;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
