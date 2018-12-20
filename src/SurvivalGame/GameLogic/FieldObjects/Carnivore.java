package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Field;
import SurvivalGame.GameLogic.FieldObject;
import SurvivalGame.GameLogic.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Carnivore extends MovingFieldObject implements Attacker, HealthObject{
    protected int health;
    protected int damage;
    private int radiusAttack;

    public Carnivore(int health, int damage, int radiusAttack){
        super();

        if(health <= 0) {
            throw new IllegalArgumentException("health must be > 0");
        }
        if(damage < 0) {
            throw new IllegalArgumentException("damage must be >= 0");
        }
        if(radiusAttack <= 0) {
            throw new IllegalArgumentException("radiusAttack must be > 0");
        }

        this.health = health;
        this.damage = damage;
        this.radiusAttack = radiusAttack;
    }

    //getDamage
    public int getDamage(){return damage;}

    public int getRadiusAttack(){ return radiusAttack;}


    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        if(health < 0) {
            throw new IllegalArgumentException("health must be >= 0");
        }
        this.health = health;
    }


    //@@@@    this has been added
    private <T extends FieldObject> List<T> getAFieldObject(){

        Field field = getField();

        field.getFieldObjects().stream()
                .filter(x -> instanceof Rock )
                .collecct(Collectors.toList());

    }



    @Override
    public List<Point> attackArea(){
        List<Point> attackArea = new ArrayList<>(radiusAttack * radiusAttack);
        int xMin = (getPoint().getX() - 3 >= 0)? (getPoint().getX() - 3) : 0;
        int xMax = (getPoint().getX() + 3 >= getField().getWidth() )? getField().getWidth() : getPoint().getX() + 3;

        int yMin = (getPoint().getY() - 3 >= 0)? (getPoint().getY() - 3) : 0;
        int yMax = (getPoint().getY() + 3 >= getField().getHeight() + 3)? getField().getHeight(): getPoint().getY() + 3;


        for(int i = xMin; i <= xMax; ++i){
            for(int j = yMin; j <= yMax; ++j){
                attackArea.add(new Point(i, j));
            }
        }
        return attackArea;
    }

    public List<Rock>

    @Override
    public boolean isRock(Point point){
        List<Point>
    }

    @Override
    public void attack(HealthObject target) {
        target.lowerHealth(damage);
    }
}
