package SurvivalGame.GameLogic.Items;

public class Hand extends Tool {
    private final String NAME = "Hand";

    public Hand(){
        super(0, 1, 1);
    }
    @Override
    public String getName() {
        return NAME;
    }
//    public Hand(){
//        super(0, "Hand",1, 1);
//    }


//    public void buildBase(int stick){
//        if(stick <= 0){
//            throw new IllegalArgumentException("stick must be > 0");
//        }
//
//        //base.setLevel(stick);
//
//        //stick.reduce(stick);
//    }
//
//    public void buildBase(){
//        this.buildBase(10);
//    }
//
//    public void makeTorch(int stick){
//        if(stick <= 0){
//            throw new IllegalArgumentException("stick must be > 0");
//        }
//        //stick.reduce(stick);
//
//
//    }
//
//    public void makeTorch(){
//        this.makeTorch(3);
//    }

}