package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Agent;
import SurvivalGame.GameLogic.Field;
import SurvivalGame.GameLogic.FieldObject;
import SurvivalGame.GameLogic.Point;

import java.io.ObjectInputFilter;
import java.lang.Math.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Carnivore extends MovingFieldObject implements Attacker, HealthObject{
    protected int health;
    protected int damage;
    private int radiusAttack;
    private List<Point> deadZone = new ArrayList<Point>((int) Math.pow(radiusAttack, 2));

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

    //This function return the shortest path to go to the agent to attack
    private Point findShortestPath(Point agent, Point l, Point d, Point r, Point u) {
        double distanceL = calculateDistance(agent, l);
        double distanceD = calculateDistance(agent, d);
        double distanceR = calculateDistance(agent, r);
        double distanceU = calculateDistance(agent, u);

        double max = distanceL;
        Point result = l;

        if(max < distanceD){
            max = distanceD;
            result = d;
        }

        if(max < distanceR){
            max = distanceR;
            result = r;
        }
        if(max < distanceU){
            max = distanceU;
            result = u;
        }
        return result;
    }


    private void goToAgent(){
        List<FieldObject> agent = aFieldObjectList(Agent.class);

        //This can be coded based on how you want animal to run to the agent

    }


    //This is used to retrieve all the Rocks existing on the Field
    private List<FieldObject> aFieldObjectList(Class<? extends FieldObject> type){

        Field field = getField();
        return field.getFieldObjects().stream()
                .filter(x -> type.isInstance(x))
                .collect(Collectors.toList());

    }

    //This method: to retrieve a list of the Points of all the rock in the deadZone
    private List<FieldObject> obstacleInDeadZone(){

        List<FieldObject> list = aFieldObjectList(Rock.class); // this contains all the rocks on the field

        List<FieldObject> obstacle = new ArrayList<>(list.size()); //filter only the rock(s) in deadZone

        for(Point point : deadZone){
            for(FieldObject fieldObject : list){
                if(fieldObject.getPoint().isTheSame(point)){
                    obstacle.add(fieldObject);
                }
            }
        }

        return obstacle;
    }


    public void circleArea(){
        int xMin = (getPoint().getX() - 3 >= 0)? (getPoint().getX() - 3) : 0;
        int xMax = (getPoint().getX() + 3 >= getField().getWidth() )? getField().getWidth() : getPoint().getX() + 3;

        int yMin = (getPoint().getY() - 3 >= 0)? (getPoint().getY() - 3) : 0;
        int yMax = (getPoint().getY() + 3 >= getField().getHeight() + 3)? getField().getHeight(): getPoint().getY() + 3;


        for(int i = xMin; i <= xMax; ++i){
            for(int j = yMin; j <= yMax; ++j){
                Point point = new Point(i, j);
                if(isIncluded(point) ){
                    deadZone.add(new Point(i, j));
                }

            }
        }
    }


    //this method will provide a list of all available points that Carnivore can attack agent
    public void attackingArea(){
        List<FieldObject> obstacle = obstacleInDeadZone();

        for(int i = 0; i < obstacle.size(); ++i){
            for(int j = 0; j < deadZone.size(); ++j) {

                if(isSameAreaWithRock(deadZone.get(j), obstacle.get(i).getPoint()) &&
                   isInShadow(deadZone.get(j), obstacle.get(i).getPoint())  ){
                        deadZone.remove(j);
                }
            }
            obstacle.remove(i); // remove the rock from the list;
        }

    }

//    public List<Point> squareArea(){
//        List<Point> attackArea = new ArrayList<>(radiusAttack * radiusAttack);
//
//        int xMin = (getPoint().getX() - 3 >= 0)? (getPoint().getX() - 3) : 0;
//        int xMax = (getPoint().getX() + 3 >= getField().getWidth() )? getField().getWidth() : getPoint().getX() + 3;
//
//        int yMin = (getPoint().getY() - 3 >= 0)? (getPoint().getY() - 3) : 0;
//        int yMax = (getPoint().getY() + 3 >= getField().getHeight() + 3)? getField().getHeight(): getPoint().getY() + 3;
//
//
//        for(int i = xMin; i <= xMax; ++i){
//            for(int j = yMin; j <= yMax; ++j){
//
//                deadZone.add(new Point(i, j));
//            }
//        }
//        return attackArea;
//    }

    //calculate distance to the current Point of Carnivore
    private double calculateDistance(Point point){
        double xCenter = getPoint().getX() + 0.5;
        double yCenter = getPoint().getY() + 0.5;

        double x = point.getX() + 0.5;
        double y = point.getY() + 0.5;

        return Math.sqrt(Math.pow((xCenter - x), 2) + Math.pow((yCenter - y), 2) );

    }

    //this function to check if a Point, whose distance to the Carnivore less than radiusAttack
    private boolean isIncluded(Point point) {
        return (calculateDistance(point) <= getRadiusAttack())? true : false;
    }

    private double calculateDistance(Point point1, Point point2){
        double x1 = point1.getX() + 0.5;
        double y1= point1.getY() + 0.5;

        double x2 = point2.getX() + 0.5;
        double y2= point2.getY() + 0.5;

        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) );

    }


//    private boolean isRock(Point point){   NON NEEDED any more
//
//        List<FieldObject> list = aFieldObjectList(Rock.class);
//
//        for(FieldObject rock : list){
//            if(point.isTheSame(rock.getPoint())){
//                return true;
//            }
//        }
//
//        return false;
//    }

    private boolean isSameAreaWithRock(Point point, Point rock){
        if(rock.getX() >= getPoint().getX() && rock.getY() >= getPoint().getY()){
            return (point.getX() >= getPoint().getX() && point.getY() >= getPoint().getY());

        }else if(rock.getX() >= getPoint().getX() && rock.getY() <= getPoint().getY()){
            return (point.getX() >= getPoint().getX() && point.getY() <= getPoint().getY());

        }else if(rock.getX() <= getPoint().getX() && rock.getY() >= getPoint().getY()){
            return (point.getX() <= getPoint().getX() && point.getY() >= getPoint().getY());
        }else{
            return (point.getX() <= getPoint().getX() && point.getY() <= getPoint().getY());
        }
    }

    private boolean isInShadow(Point point, Point rock){
        double distance = calculateDistance(point);

        double rockAngle = (rock.getY() + 0.5)  / (rock.getX() + 0.5); //central point

        double pointAngle = (point.getY() + 0.5) / (point.getX() + 0.5);//central point

        double smallAngle = Math.asin(0.5/distance); //because the diameter for every tile is 1


        return ((pointAngle >= rockAngle - smallAngle) && (pointAngle <= rockAngle + smallAngle) )? true : false;

    }

    @Override
    public void attack(HealthObject target) {
        target.lowerHealth(damage);
    }
}
