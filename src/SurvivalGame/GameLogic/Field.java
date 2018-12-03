package SurvivalGame.GameLogic;
import java.util.List;

public class Field {
    private List<FieldObject> fieldObjects;
    Tile[][] grid = new Tile[10][10];

    public List<FieldObject> getFieldObjects() {
        return fieldObjects;
    }

    public void setFieldObjects(List<FieldObject> fieldObjects) {
        this.fieldObjects = fieldObjects;
    }
}
