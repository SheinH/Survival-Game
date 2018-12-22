package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.MovingFieldObject;

import java.util.List;
import java.util.function.Consumer;

public abstract class FieldObject{
    private transient Field field;
    private Point point;
    private String name;
    //* CONSTRUCTOR(S)
    public FieldObject() {

        // not implemented yet
    }

    public FieldObject(Field field, Point point, String name) {
        this.field = field;
        this.point = point;
        this.name = name;
    }


    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    public Tile getTile() {
        return field.getTile(point);
    }


    //destroy() method
    public void destroy() {
    }

    public String getName() {
        return name;
    }

    //
    public void setName(String name) {
        this.name = name;
    }


    //*update() method

    public abstract void update();


    public abstract char getChar();
}