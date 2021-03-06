package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Carnivore extends MovingFieldObject implements Attacker, HealthObject{
    protected int damage;
    private int radiusZone;
    private transient ItemsList loot;

    @Override
    public ItemsList getLoot() {
        return loot;
    }

    public void setLoot(ItemsList loot) {
        this.loot = loot;
    }

    private List<Point> deadZone = new ArrayList<Point>((int) Math.pow(radiusZone, 2));
    private List<Point> attackZone;

    public Carnivore(int health, int damage, int radiusZone){
        super(health);

        if(damage < 0) {
            throw new IllegalArgumentException("damage must be >= 0");
        }
        if(radiusZone <= 0) {
            throw new IllegalArgumentException("radiusZone must be > 0");
        }

        this.damage = damage;
        this.radiusZone = radiusZone;
        attackZone = new ArrayList<>();

    }

    //getAttackZone()
    public List<Point> getAttackZone(){
        return attackZone;
    }

    public void setAttackZone() {
        List<Point> list = new ArrayList<>(4);
        list.add(new Point(getPoint().getY() - 1, getPoint().getX() ) ) ;//up
        list.add(new Point (getPoint().getY() + 1, getPoint().getX() ) );//down
        list.add(new Point(getPoint().getY(), getPoint().getX() - 1) ); //left
        list.add(new Point(getPoint().getY(), getPoint().getX() + 1) );//right

        this.attackZone = list;
    }


    //getDamage
    public int getDamage(){return damage;}

    public int getradiusZone(){ return radiusZone;}

    @Override
    public void update(){
        Agent agent = getField().getAgent();
        boolean bool = isAgentInAttackZone(agent);
        if (actionTime > 0)
            actionTime--;
        else if(bool){
            System.out.println("ATTACKING");
            attack(getField().getAgent());
            System.out.println("ATTACK SUCCESSFUL");
        }
        else if(isAgentInDeadZone(getField().getAgent().getPoint())){
            System.out.println("GOING TO AGENT");
            goToAgent();
            System.out.println("SUCCESSFULLY WENT TO AGENT");
        }
        else {
            moveForward();
            changeDirection();
            addMoveTime();
        }
    }

    @Override
    public void attack(MovingFieldObject agent) {
        if(agent instanceof Agent){
            addMoveTime(10);
            agent.lowerHealth(getDamage());
        } else {
            return;
        }
    }

    public void doAttack(){
        if(isAgentInAttackZone(getField().getAgent().getPoint())){
            attack(getField().getAgent());
        } else{
            return;
        }
    }

    public void doAttack(Agent agent){
        if(isAgentInAttackZone(agent)){
            attack(agent);
        } else{
            return;
        }
    }


    public boolean isAgentInAttackZone(Point agentPoint){
        updateAttackZone();
        for(Point point : attackZone){
            if(point.equals(agentPoint)){
                return true;
            }
        }
        return false;
    }

    public boolean isAgentInAttackZone(Agent agent){
        updateAttackZone();
        if(attackZone.contains(agent.getPoint())){
            return true;
        }
        return false;
    }

    public void updateAttackZone(){
        attackZone.clear(); //get rid of all the old points

        // adding new 4 points after moving to a new area
        attackZone.add(new Point(getPoint().getY() - 1, getPoint().getX() ) );//up
        attackZone.add(new Point(getPoint().getY() + 1, getPoint().getX() ) );//down
        attackZone.add(new Point(getPoint().getY(), getPoint().getX() - 1 ) ); //left
        attackZone.add(new Point(getPoint().getY(), getPoint().getX() + 1 ) );//right
    }


    private Point findShortestSpot(Point agent, Point l, Point d, Point r, Point u) {
        double distanceL = calculateDistance(agent, l);
        double distanceD = calculateDistance(agent, d);
        double distanceR = calculateDistance(agent, r);
        double distanceU = calculateDistance(agent, u);

        double min = distanceL;
        Point result = l;

        if(min > distanceD){
            min = distanceD;
            result = d;
        }

        if(min > distanceR){
            min = distanceR;
            result = r;
        }
        if(min > distanceU){
            min = distanceU;
            result = u;
        }

        return result;
    }


    //This function return the shortest path to go to the agent to attack
//    private Point findShortestSpot(Point agentPoint, Point ... points ) {
//
//        Point result = points[0];
//        double min = calculateDistance(getField().getAgent().getPoint(), result);
//
//        for(Point point : points){
//            double distance = calculateDistance(agentPoint, point);
//
//            if(min > distance){
//                min = distance;
//                result = point;
//            }
//        }
//
//        return result;
//    }

    private void moveHorizontal(Point des) {
        if(des.getX() == getPoint().getX()) {return;}

        if(des.getX() > getPoint().getX()){//move to the right 1 spot
            setPoint(getField().getTileGrid()[getPoint().getY()][getPoint().getX() + 1].getPoint());
        }
        else{//move to the left 1 spot
            setPoint(getField().getTileGrid()[getPoint().getY()][getPoint().getX() - 1].getPoint());
        }
    }


    private void moveVertical(Point des){

        if(des.getY() == getPoint().getY()) {return;}

        if(des.getY() > getPoint().getY()){ // move down down spot
            setPoint(getField().getTileGrid()[getPoint().getY() + 1][getPoint().getX()].getPoint());
        }
        else { //move up 1 spot
            setPoint(getField().getTileGrid()[getPoint().getY() - 1][getPoint().getX()].getPoint());
        }
    }

    private boolean isAgentInDeadZone(Point agentPoint){
//        List<FieldObject> agent = aFieldObjectList(Agent.class);
//        Point agentPoint = agent.get(0).getPoint();
        deadZoneArea();
        for(Point point : deadZone){
            if(point.isEqual(agentPoint)){
                return true;
            }
        }
        return false;
    }

    public void update_test(){
        super.update();
        goToAgent();
    }

    public void goToAgent(){
        Agent agent = getField().getAgent();
        Point agentPoint= agent.getPoint();
        if(!isAgentInDeadZone(agentPoint)) return;

        int x = agentPoint.getX();
        int y = agentPoint.getY();
        int myX = getPoint().getX();
        int myY = getPoint().getY();

        int dx = x-myX;
        int dy = y-myY;

        if(Math.abs(dx) > Math.abs(dy) || (Math.abs(dx) == Math.abs(dy) && Math.random() > 0.5) ){
            if(dx > 0)
                setDirection(Direction.RIGHT);
            else if(dx < 0)
                setDirection(Direction.LEFT);
        }
        else{
            if(dy > 0)
                setDirection(Direction.DOWN);
            else if(dy < 0)
                setDirection(Direction.UP);
        }
        moveForward();
        addMoveTime();
    }

    //this method will provide a list of all available points that Carnivore can find the agent
    public void deadZoneArea(){
        circleArea();
        List<FieldObject> obstacle = obstacleInDeadZone();

        for(int i = 0; i < obstacle.size(); ++i){
            for(int j = 0; j < deadZone.size(); ++j) {

                if(isSameAreaWithRock(deadZone.get(j), obstacle.get(i).getPoint()) &&
                        isInShadow(deadZone.get(j), obstacle.get(i).getPoint())  ){
                    deadZone.remove(j);
                }
            }
            obstacle.remove(i); // remove the rock from the list to check other rocks;
        }

    }

    //This is used to retrieve all the instance of type existing on the Field
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
                if(fieldObject.getPoint().isEqual(point)){
                    obstacle.add(fieldObject);
                }
            }
        }

        return obstacle;
    }

    //this method results in the whole circle attacking area (including rock)
    public void circleArea(){
        int xMin = (getPoint().getX() - 3 >= 0)? (getPoint().getX() - 3) : 0;
        int xMax = (getPoint().getX() + 3 >= getField().getWidth() )? getField().getWidth() : getPoint().getX() + 3;

        int yMin = (getPoint().getY() - 3 >= 0)? (getPoint().getY() - 3) : 0;
        int yMax = (getPoint().getY() + 3 >= getField().getHeight() + 3)? getField().getHeight(): getPoint().getY() + 3;


        for(int i = xMin; i < xMax; ++i){
            for(int j = yMin; j < yMax; ++j){
                Point point = new Point(i, j);
                if(isIncluded(point) && getField().inBounds(point)){
                    deadZone.add(point);
                }
            }
        }
    }


    //this function to check if a Point, whose distance to the Carnivore less than radiusZone
    private boolean isIncluded(Point point) {
        return (calculateDistance(point) <= getradiusZone())? true : false;
    }

    //calculate distance to the current Point of Carnivore
    private double calculateDistance(Point point){
        double xCenter = getPoint().getX() + 0.5;
        double yCenter = getPoint().getY() + 0.5;

        double x = point.getX() + 0.5;
        double y = point.getY() + 0.5;

        return Math.sqrt(Math.pow((xCenter - x), 2) + Math.pow((yCenter - y), 2) );

    }

    //calculate distance between 2 random points
    private double calculateDistance(Point point1, Point point2){
        double x1 = point1.getX() + 0.5;
        double y1= point1.getY() + 0.5;

        double x2 = point2.getX() + 0.5;
        double y2= point2.getY() + 0.5;

        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) );

    }


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

        double rockAngle;
        double pointAngle;

        double rockX = rock.getX() - getPoint().getX();
        double rockY = rock.getY() - getPoint().getY();
        double pointX = point.getX() - getPoint().getX();
        double pointY = point.getY() - getPoint().getY();


        if(rockX == 0 && rockY < 0){
            rockAngle = -Math.PI/2;
        } else if (rockX == 0 & rockY > 0){
            rockAngle = Math.PI/2;
        }
        else{
            rockAngle = Math.atan(rockY / rockX);
        }

        if(pointX == 0 && pointY < 0){
            pointAngle = -Math.PI/2;
        } else if (pointX == 0 & pointY > 0){
            pointAngle = Math.PI/2;
        }
        else{
            pointAngle = Math.atan(pointY / pointX);
        }


        double smallAngle = Math.asin(0.5/distance); //because the diameter for every tile is 1


        return ((pointAngle >= rockAngle - smallAngle) && (pointAngle <= rockAngle + smallAngle) )? true : false;

    }
}
