package SurvivalGame.GameLogic.FieldObjects;

public interface HealthObject {
    int getHealth();
    void setHealth(int difference);
    default void lowerHealth(int difference){
        setHealth(getHealth() - difference);
    }
    default void increaseHealth(int difference) {setHealth(getHealth() + difference);}

}
