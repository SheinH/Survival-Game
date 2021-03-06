package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.ItemsList;

public interface HealthObject {
    int getHealth();
    void setHealth(int health);
    int getMaxHealth();
    ItemsList getLoot();


    default void lowerHealth(int difference){setHealth(Math.max(getHealth() - difference,0)); }

    default void boostHealth(int difference) {setHealth(getHealth() + difference);}

}
