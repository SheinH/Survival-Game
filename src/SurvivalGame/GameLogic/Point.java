package SurvivalGame.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.x  = x;
        this.y  = y;
    }

    //#getter methods

    public int getX(){return x;}

    public int getY(){return y;}

    public List<Point> getNeighbors(){
        ArrayList<Point> list = new ArrayList<>(4);
        list.add(new Point(y-1,x));
        list.add(new Point(y+1,x));
        list.add(new Point(y,x-1));
        list.add(new Point(y,x+1));
        return list;
    }

    public double distanceTo(Point other){
        return Math.sqrt(Math.pow(x - other.x,2) + Math.pow(y - other.y,2));
    }

    public Point offsetTo(Point other){
        return new Point(other.x - x,other.y - y);
    }

    @Override
    public String toString() {
        return "[" + y + "," + x + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isEqual(Point point){
        return( this.x == point.getX() && this.y == point.getY() );
    }

}