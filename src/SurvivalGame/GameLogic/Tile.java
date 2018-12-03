package SurvivalGame.GameLogic;
import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    public List<MovingFieldObject> objects;
    private Terrain terrain;
    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
