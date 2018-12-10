package SurvivalGame.GameLogic;

public abstract class FieldObject {
    private Field field;
    private Point point;
    private String name;

    //* CONSTRUCTOR(S)
    public FieldObject() {

        // not implemented yet
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

    public Tile getTile(){
        return field.getTile(point);
    }

    //*update() method

    public abstract void update();

    //destroy() method
    public void destroy(){}

    public abstract char getChar();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldObject(Field field, Point point, String name) {
        this.field = field;
        this.point = point;
        this.name = name;
    }

    public Field getField() {
        return field;
    }
}