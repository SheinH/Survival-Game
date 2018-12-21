package SurvivalGame.GameLogic.FieldObjects;

public interface HealthObject {
    int getHealth();
    void setHealth(int health);
    int getMaxHealth();


    default void lowerHealth(int difference){setHealth(getHealth() - difference); }

    default void boostHealth(int difference) {setHealth(getHealth() + difference);}

}
