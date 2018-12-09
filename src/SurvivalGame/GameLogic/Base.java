package SurvivalGame.GameLogic;

public class Base extends FieldObject{
    private int [] baseCapacity = new int[6];

    @Override
    public void update() {

    }

    @Override
    public char getChar() {
        return 0;
    }

    //index at 1 for number of sticks
    //         2               stones
    //         3               torches
    //         4               spears
    //         5               berries
    //         6 for amount of meat
    public Base() {

        for(int i = 0; i < 6; ++i){
            this.baseCapacity[i] = 0;
        }


    }

    //getter methods


    public int getStick(){return baseCapacity[0];}


    public  int getTorch() {return baseCapacity[1];}


    public  int getStone() {return baseCapacity[2];}


    public  int getSpear() {return baseCapacity[3];}


    public  int getBerry() {return baseCapacity[4];}


    public  int getMeat() {return baseCapacity[5];}

    //setter methods()


    public  void setStick (int difference){
        if(this.baseCapacity[0] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[0];
        this.baseCapacity[0] = oldValue + difference;
    }


    public  void setTorch (int difference) {
        if(this.baseCapacity[1] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[1];
        this.baseCapacity[1] = oldValue + difference;
    }


    public  void setStone (int difference){
        if(this.baseCapacity[2] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[2];
        this.baseCapacity[2] = oldValue + difference;
    }


    public  void setSpear (int difference){
        if(this.baseCapacity[3] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[3];
        this.baseCapacity[3] = oldValue + difference;
    }


    public  void setBerry (int difference){
        if(this.baseCapacity[4] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[4];
        this.baseCapacity[4] = oldValue + difference;
    }


    public  void setMeat (int difference){
        if(this.baseCapacity[5] + difference < 0){
            throw new IllegalArgumentException("You cannot use " +
                    "more than what you have");
        }
        int oldValue = this.baseCapacity[5];
        this.baseCapacity[5] = oldValue + difference;
    }
}