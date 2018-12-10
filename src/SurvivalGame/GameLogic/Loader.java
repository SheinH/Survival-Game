package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
    public static Tile makeTile(char c){
        switch(c){
            case 'W':
                return new Tile(Terrain.WATER);
            case 'D':
                return new Tile(Terrain.DESERT);
            default:
                return new Tile(Terrain.GRASS);
        }
    }

    public static FieldObject makeFieldObject(char c){
        switch(c){
            case 'A':
                return new Agent();
            case 'R':
                return new Rabbit();
            case 'C':
                return new Crocodile();
            case 'F':
                return new Fish();
            case 'L':
                return new Lion();
            case 'H':
                return new Horse();
            case 'T':
                return new Tree();
            case 'B':
                return new Bush();
            case 'r':
                return new Rock();
            default:
                return null;
        }
    }

    public static Field loadTiles(Scanner sc){
        ArrayList<String> lines = new ArrayList<>();
        for(;;){
            String line = sc.nextLine();
            if(line.length() == 0)
                break;
            else
                lines.add(line);
        }
        int h = lines.size();
        int w = lines.get(0).length();
        Field field = new Field(h,w);
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                char c = lines.get(y).charAt(x);
                Tile t = makeTile(c);
                Point p = new Point(y,x);
                field.setTile(p,t);
            }
        }
        return field;
    }

    public static void loadObjects(Scanner sc, Field field){
        ArrayList<String> lines = new ArrayList<>();
        for(;;){
            String line = sc.nextLine();
            if(line.length() == 0)
                break;
            else
                lines.add(line);
        }
        int h = field.getHeight();
        int w = field.getWidth();
        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                char c = lines.get(y).charAt(x);
                Point p = new Point(y,x);
                FieldObject obj = makeFieldObject(c);
                if(obj instanceof  Agent){
                    Agent a = (Agent) obj;
                    field.getGame().setAgent(a);
                }
                if(obj != null)
                    field.addFieldObject(obj,p);
            }
        }
    }
}
