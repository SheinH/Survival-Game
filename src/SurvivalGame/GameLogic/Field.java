package SurvivalGame.GameLogic;
import java.util.List;


public class Field extends Tile{
    private List<FieldObject> fieldObjects;
    public List<FieldObject> getFieldObjects() {
        return fieldObjects;
    }

    public void setFieldObjects(List<FieldObject> fieldObjects) {
        this.fieldObjects = fieldObjects;
    }

    public static void main(String[] args){

        Tile[][] grid = new Tile[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                grid[i][j]=new Tile();
            }
        }

            }
}




