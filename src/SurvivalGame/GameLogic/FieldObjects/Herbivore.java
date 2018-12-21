package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.ItemsList;

public abstract class Herbivore extends MovingFieldObject implements HealthObject{

    private transient ItemsList loot;

    @Override
    public ItemsList getLoot() {
        return loot;
    }

    public void setLoot(ItemsList loot) {
        this.loot = loot;
    }
    public Herbivore(int health){
        super(health);
    }

    @Override
    public int getMaxHealth(){return 0;}
}
