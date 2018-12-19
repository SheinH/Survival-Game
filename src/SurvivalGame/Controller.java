package SurvivalGame;

import SurvivalGame.GameLogic.*;
import SurvivalGame.GameLogic.FieldObjects.*;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    @FXML
    private GridPane mainGrid;
    @FXML
    private BorderPane mainPane;
    private Scene mainScene;
    private SurvivalGame game;
    private HashMap<Class<? extends FieldObject>,Image> objectImages;
    private HashMap<Terrain,Image> terrainImages;
    private List<ImageView> imageViewList;
    private HashMap<FieldObject, ImageView> objectImageViews;

    public Controller(SurvivalGame g) {
        game = g;
        g.setUpdateGui(() -> updateTileGrid());
        terrainImages = new HashMap<>();
        objectImages = new HashMap<>();
        imageViewList = new ArrayList<>();
        objectImageViews = new HashMap<>();
        loadImages();
    }


    private void handleKeyPress(KeyEvent key) {
        switch(key.getCode())
        {
            case W:
                game.pause();
                game.getAgent().setDirection(Direction.UP);
                break;
            case S:
                game.pause();
                game.getAgent().setDirection(Direction.DOWN);
                break;
            case A:
                game.pause();
                game.getAgent().setDirection(Direction.LEFT);
                break;
            case D:
                game.pause();
                game.getAgent().setDirection(Direction.RIGHT);
                break;
            case T:
                game.update();
                break;
        }
        game.unPause();

    }

    private void handleKeyRelease(KeyEvent key) {
        switch(key.getCode())
        {
            case W:
            case S:
            case A:
            case D:
                game.pause();
                game.getAgent().setDirection(Direction.NONE);
                game.unPause();
        }

    }

    private void loadImages(){
        String terrainFolder = "file:res" + File.separator + "Terrain_Pictures" + File.separator;
        Image grassimage = new Image(terrainFolder + "Grass64.png",32,32,true,false);
        Image desertimage = new Image(terrainFolder + "Desert64.png",32,32,true,false);
        Image waterimage = new Image(terrainFolder + "Water64.png",32,32,true,false);
        terrainImages.put(Terrain.GRASS,grassimage);
        terrainImages.put(Terrain.DESERT,desertimage);
        terrainImages.put(Terrain.WATER,waterimage);
        
        String objectsFolder = "file:res" + File.separator + "FieldObject_Pictures" + File.separator;
        Image bearimage = new Image(objectsFolder + "Bear64.png",32,32,true,false);
        Image treeimage = new Image(objectsFolder + "Tree64.png",32,32,true,false);
        Image rabbitimage = new Image(objectsFolder + "Rabbit64.png",32,32,true,false);
        Image bushimage = new Image(objectsFolder + "Bush64.png",32,32,true,false);
        Image fishimage = new Image(objectsFolder + "Fish64.png",32,32,true,false);
        Image crocimage = new Image(objectsFolder + "Croc64.png",32,32,true,false);
        Image lionimage = new Image(objectsFolder + "Lion64.png",32,32,true,false);
        Image rockimage = new Image(objectsFolder + "Rock64.png",32,32,true,false);
        Image agentImage = new Image(objectsFolder + "Agent.png",32,32,true,false);
        objectImages.put(Bear.class, bearimage);
        objectImages.put(Tree.class, treeimage);
        objectImages.put(Rabbit.class, rabbitimage);
        objectImages.put(Bush.class, bushimage);
        objectImages.put(Fish.class, fishimage);
        objectImages.put(Crocodile.class, crocimage);
        objectImages.put(Lion.class, lionimage);
        objectImages.put(Rock.class, rockimage);
        objectImages.put(Agent.class, agentImage);
    }

    public void requestFocus(){
        mainGrid.requestFocus();
    }

    public void updateTileGrid(){
        for(FieldObject obj : game.getField().getFieldObjects()){
            GridPane.setConstraints(objectImageViews.get(obj),obj.getPoint().getX(),obj.getPoint().getY());
        }
    }

    private void loadTerrainImages(){
        Field field = game.getField();
        for(int y = 0; y < field.getHeight(); y++){
            for(int x = 0; x < field.getWidth(); x++){
                Tile tile = field.getTile(new Point(y,x));
                ImageView imageView = new ImageView(terrainImages.get(tile.getTerrain()));
                mainGrid.add(imageView,x,y);
            }
        }
    }

    private void loadObjectImages(){
        List<FieldObject> objects = game.getField().getFieldObjects();
        for(FieldObject o : objects){
            ImageView imageView = new ImageView(objectImages.get(o.getClass()));
            mainGrid.add(imageView,o.getPoint().getX(),o.getPoint().getY());
            objectImageViews.put(o,imageView);
        }

    }

    public void loadScene(){
        mainScene = mainGrid.getScene();
        mainScene.setOnKeyPressed((e) -> handleKeyPress(e));
        mainScene.setOnKeyReleased((e) -> handleKeyRelease(e));
    }


    @FXML
    private void initialize(){
        loadTerrainImages();
        loadObjectImages();
        updateTileGrid();
        game.setUpdateGui(() -> updateTileGrid());
    }
}
