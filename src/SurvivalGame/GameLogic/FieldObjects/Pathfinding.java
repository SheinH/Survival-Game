package SurvivalGame.GameLogic.FieldObjects;

import SurvivalGame.GameLogic.Field;
import SurvivalGame.GameLogic.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Pathfinding {

    Field field;
    HashMap<Point,Integer> traversaldistance;
    List<Point> labelled;
    List<Point> current;
    int generation;
    MovingFieldObject object;
    Point destination;

    public Pathfinding(Field field) {
        this.field = field;
        labelled = new ArrayList<>();
        current = new ArrayList<>();
        traversaldistance = new HashMap<>();
        generation = 0;
    }

    private void clear(){
        labelled = new ArrayList<>();
        current = new ArrayList<>();
        traversaldistance = new HashMap<>();
        generation = 0;
    }
    public List<Point> findPath(MovingFieldObject object, Point destination){
        if(!object.canMoveTo(destination))
            return null;
        this.object = object;
        this.destination = destination;
        labelPoint(object.getPoint(),0);
        while(!labelled.contains(destination))
            advanceGeneration();
        List<Point> path = backtrack();
        clear();
        return path;
    }

    public void labelPoint(Point p, int n){
        traversaldistance.put(p,n);
        labelled.add(p);
        current.add(p);
        System.out.println(traversaldistance.keySet().contains(p));
    }

    public void advanceGeneration(){
        List<Point> previous = current;
        current = new ArrayList<>();
        previous.forEach(point -> {
            List<Point> neighbors = point.getNeighbors();
            neighbors.forEach(neighbor -> {
                if(field.inBounds(neighbor))
                    if(!labelled.contains(neighbor))
                        if(object.canMoveTo(neighbor))
                            labelPoint(neighbor, generation);
            });
        });
        generation++;
    }

    public List<Point> backtrack(){
        int dist = traversaldistance.get(destination);
        List<Point> path = new ArrayList<>(dist);
        Point currentPoint = destination;
        path.add(destination);
        while(dist != 0) {
            dist--;
            List<Point> neighbors = currentPoint.getNeighbors();
            for (Point neighbor : neighbors) {
                if (labelled.contains(neighbor) && traversaldistance.get(neighbor).intValue() == dist) {
                    path.add(neighbor);
                    currentPoint = neighbor;
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }
}
