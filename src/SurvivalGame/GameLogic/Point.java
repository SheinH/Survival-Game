package SurvivalGame.GameLogic;

public class Point{
    private final int x;
    private final int y;

    //#CONSTRUCTORS
    public Point(int x){
        this(x, 0);
    }

    public Point(){
        this(0,0);
    }

    public Point(int y, int x){
        if(x < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }

        if (y < 0) {
            throw new IllegalArgumentException("y must be >= 0");
        }

        this.x  = x;
        this.y  = y;
    }

    //#getter methods

    public int getX(){return x;}

    public int getY(){return y;}


    public boolean isEqual(Point point){
        return( this.x == point.getX() && this.y == point.getY() );
    }

}