package SurvivalGame.GameLogic.FieldObjects;

public interface HealthObject {
    int getHealth();
    void setHealth(int i);
    default void lowerHealth(int i){
        setHealth(getHealth() - i);
    }
}
