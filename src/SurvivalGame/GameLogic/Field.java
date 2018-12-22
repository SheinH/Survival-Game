package SurvivalGame.GameLogic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import SurvivalGame.GameLogic.FieldObjects.*;


public class Field implements Iterable<Tile>{

    private SurvivalGame game;
    private Tile[][] grid;
    private int height;
    private int width;
    private List<FieldObject> fieldObjects;
    private Agent agent;



    public Field(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Tile[height][width];
        fieldObjects = new ArrayList<>();
    }

    public SurvivalGame getGame() {
        return game;
    }

    public void setGame(SurvivalGame game) {
        this.game = game;
    }

    public Agent getAgent() {
        return agent;
    }

    public Tile[][] getTileGrid(){ return grid;}


    public void setTile(Point p, Tile t){
        grid[p.getY()][p.getX()] = t;
        t.setPoint(p);
    }

    public Tile getTile(Point p){
        return grid[p.getY()][p.getX()];
    }


    public Point getFieldPoint(int y, int x){
        return grid[y][x].getPoint();
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public List<FieldObject> getFieldObjects() {
        return fieldObjects;
    }

    public void addFieldObject(FieldObject obj, Point p){
        var objects = getTile(p).getObjects();

        objects.add(obj);
        if(obj instanceof Agent) {
            fieldObjects.add(0, obj);
            this.agent = (Agent) obj;
        }
        else
            fieldObjects.add(obj);
        obj.setPoint(p);
        obj.setField(this);
    }


    public boolean inBounds(Point p){
        return p.getX() >= 0 && p.getX() < width &&
                p.getY() >= 0 && p.getY() < height;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Point p = new Point(y,x);
                Tile t = getTile(p);
                builder.append(t.toChar());
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    @Override
    public void forEach(Consumer<? super Tile> action) {
        TileIterator iterator = new TileIterator();
        while(iterator.hasNext()){
            action.accept(iterator.next());
        }
    }

    @Override
    public Spliterator<Tile> spliterator() {
        return null;
    }

    class TileIterator implements Iterator<Tile>{
        int x, y;

        public TileIterator() {
            x = y = 0;
        }

        @Override
        public boolean hasNext() {
            if(x + 1 == width && y + 1 == height)
                return false;
            return true;
        }

        @Override
        public Tile next() {
            Tile t =  grid[y][x];
            x++;
            if(x == width){
                x = 0;
                y++;
            }
            return t;
        }
    }
}




