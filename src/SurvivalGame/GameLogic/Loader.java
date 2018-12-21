package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.FieldObjects.*;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
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
                return new Agent(100);
            case 'R':
                return loadObject(Rabbit.class);
            case 'C':
                return loadObject(Crocodile.class);
            case 'F':
                return loadObject(Fish.class);
            case 'L':
                return loadObject(Lion.class);
            case 'H':
                return loadObject(Horse.class);
            case 'T':
                return new Tree();
            case 'B':
                return new Bush();
            case 'r':
                return new Rock();
            case 'b':
                return loadObject(Bear.class);
                default:
                return null;
        }
    }

    public static <T extends FieldObject> T loadObject(Class<T> type){
        String input = "";
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("TEST.json")));
            StringBuilder builder = new StringBuilder();
            while (sc.hasNextLine())
                builder.append(sc.nextLine());
            input = builder.toString();
        }
        catch(Exception whatever){

        }
        JsonElement element = new JsonParser().parse(input);
        JsonObject obj = element.getAsJsonObject();
        String typename = type.getSimpleName();
        JsonElement jsonelement = obj.get(typename);
        System.out.println(new Gson().toJson(jsonelement));
        T lion = new Gson().fromJson(jsonelement, type);
        return lion;
    }
    public static Lion makeLionFromGSON(){
        String input = "";
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("TEST.json")));
            StringBuilder builder = new StringBuilder();
            while (sc.hasNextLine())
                builder.append(sc.nextLine());
            input = builder.toString();
        }
        catch(Exception whatever){

        }
            JsonElement element = new JsonParser().parse(input);
            JsonObject obj = element.getAsJsonObject();
            JsonElement jsonlion = obj.get("Lion");
            System.out.println(new Gson().toJson(jsonlion));
            Lion lion = new Gson().fromJson(jsonlion, Lion.class);
            System.out.println(lion.getMaxHealth());
            lion.setHealth(lion.getMaxHealth());
            return lion;
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
