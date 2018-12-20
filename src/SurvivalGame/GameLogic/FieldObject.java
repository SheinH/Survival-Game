package SurvivalGame.GameLogic;

public abstract class FieldObject {
    private Field field;
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

//    public Point getPointAt(int x, int y){
//        if(x < 0) {
//            throw new IllegalArgumentException("x must be >= 0");
//        }
//
//        if (y < 0) {
//            throw new IllegalArgumentException("y must be >= 0");
//        }
//    }

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



}