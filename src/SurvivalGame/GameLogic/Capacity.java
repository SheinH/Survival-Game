package SurvivalGame.GameLogic;

public interface Capacity {

    //getter methods

    public abstract int getStick();

    public abstract int getTorch();

    public abstract int getStone();

    public abstract int getSpear();

    public abstract int getBerry();

    public abstract int getMeat();

    //setter Methods()
    // difference can be negative or positive
    // positive when agent picks, catches or makes
    // negative when agent uses items

    public abstract void setStick (int difference);

    public abstract void setTorch (int difference);

    public abstract void setStone (int difference);

    public abstract void setSpear (int difference);

    public abstract void setBerry (int difference);

    public abstract void setMeat (int difference);

}