package SurvivalGame.GameLogic;
import java.util.ArrayList;
import java.util.List;


public class Field{
    private SurvivalGame game;

    public SurvivalGame getGame() {
        return game;
    }

    public void setGame(SurvivalGame game) {
        this.game = game;
    }

    private Tile[][] grid;
    private int height,width;
    private List<FieldObject> fieldObjects;
    public List<FieldObject> getFieldObjects() {
        return fieldObjects;
    }

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Tile[height][width];
        fieldObjects = new ArrayList<>();
    }

    public void setTile(Point p, Tile t){
        grid[p.getY()][p.getX()] = t;
        t.setPoint(p);
    }

    public Tile getTile(Point p){
        return grid[p.getY()][p.getX()];
    }

    public Tile[][] getTileGrid(){ return grid;}

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void addFieldObject(FieldObject obj, Point p){
        var objects = getTile(p).getObjects();
        objects.add(obj);
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
}




