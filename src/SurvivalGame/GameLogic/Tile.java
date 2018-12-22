package SurvivalGame.GameLogic;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tile implements Observable<Tile>{

    private Point point;
    private List<FieldObject> objects;
    private ItemsList itemsList;
    private Terrain terrain;
    private ObservableWrapper<Tile> observers;



    public Tile(Terrain terrain) {
        this.terrain = terrain;
        this.objects = new ArrayList<>();
        itemsList = new ItemsList();
        observers = new ObservableWrapper<>(this);
        itemsList.addListener(i -> observers.update());
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }

    public ItemsList getItemsList() {
        return itemsList;
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

    @Override
    public void addListener(Consumer<Tile> listener) {
        observers.addListener(listener);
    }

    @Override
    public void removeListener(Consumer<Tile> listener) {
        observers.removeListener(listener);
    }
}
