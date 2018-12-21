package SurvivalGame.GameLogic;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private Point point;
    private List<FieldObject> objects;

    public ItemsList getItemsList() {
        return itemsList;
    }

    private ItemsList itemsList;
    private Terrain terrain;

    public Tile(Terrain terrain) {
        this.terrain = terrain;
        this.objects = new ArrayList<>();
        itemsList = new ItemsList();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }



    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }


    public boolean hasObject(){
        return !objects.isEmpty();
    }

    public boolean hasItem(){
        return !itemsList.isEmpty();
    }

    public List<FieldObject> getObjects() {
        return objects;
    }


    //
    public char toChar(){
        if(objects.size() > 0) {
            return objects.get(0).getChar();
        }
        else{
            return terrain.getCharacter();
        }
    }
}
