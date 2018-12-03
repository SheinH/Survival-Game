package SurvivalGame.GameLogic;

public class Hand extends Tool{
    super(5, 1, 0, "hand");

    public void buildBase(int stick){
        if(stick <= 0){
            throw new IllegalArgumentException("stick must be > 0");
        }

        base.setLevel(stick);

        //stick.reduce(stick);
    }

    public void buildBase(){
        this.buildBase(10);
    }

    public void makeTorch(int stick){
        if(stick <= 0){
            throw new IllegalArgumentException("stick must be > 0");
        }
        //stick.reduce(stick);


    }

    public void makeTorch(){
        this.makeTorch(3);
    }

}