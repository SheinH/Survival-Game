package SurvivalGame;

import SurvivalGame.GameLogic.*;
import SurvivalGame.GameLogic.FieldObjects.*;
import SurvivalGame.GameLogic.Items.*;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private GridPane itemGrid;
    @FXML
    private Button pauseButton;
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

    @FXML
    private void initialize(){
        loadTerrainImages();
        loadObjectImages();
        loadItemImages();
        updateTileGrid();
        game.setUpdateGui(() -> updateTileGrid());
        pauseButton.setOnMouseClicked((e) -> {
            if(game.isPaused()) {
                game.unPause();
                pauseButton.setText("Pause");
            }
            else {
                game.pause();
                pauseButton.setText("Unpause");
            }
        });
        itemGrid.setGridLinesVisible(true);
        itemGrid.setAlignment(Pos.CENTER);
    }


    private void handleKeyPress(KeyEvent key) {
        game.getGameLock().lock();
        switch(key.getCode())
        {
            case W:
                game.getAgent().setDirection(Direction.UP);
                break;
            case S:
                game.getAgent().setDirection(Direction.DOWN);
                break;
            case A:
                game.getAgent().setDirection(Direction.LEFT);
                break;
            case D:
                game.getAgent().setDirection(Direction.RIGHT);
                break;
            case E:
                game.getAgent().changeequippedItem();
                loadItemImages();
        }
        game.getGameLock().unlock();

    }

    private void handleKeyRelease(KeyEvent key) {
        switch(key.getCode())
        {
            case W:
            case S:
            case A:
            case D:
                game.getGameLock().lock();
                game.getAgent().setDirection(Direction.NONE);
                game.getGameLock().unlock();
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
                Image terrainImage = terrainImages.get(tile.getTerrain());
                ImageView imageView = new ImageView(terrainImage);
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

    String toolFolder = "file:res" + File.separator + "Tool_Pictures" + File.separator;
    Image berryimage = new Image(toolFolder + "Berry32.png",32,32,true,false);
    Image fistimage = new Image(toolFolder + "Fist32.png",32,32,true,false);
    Image meatimage = new Image(toolFolder + "Meat32.png",32,32,true,false);
    Image spearimage = new Image(toolFolder + "Spear32.png",32,32,true,false);
    Image stickimage = new Image(toolFolder + "Stick32.png",32,32,true,false);
    Image stoneimage = new Image(toolFolder + "Stone32.png",32,32,true,false);
    Image torchimage = new Image(toolFolder + "Torch32.png",32,32,true,false);
    Image berry2image = new Image(toolFolder + "BerryGrid.png",32,32,true,false);
    Image fist2image = new Image(toolFolder + "FistGrid.png",32,32,true,false);
    Image meat2image = new Image(toolFolder + "MeatGrid.png",32,32,true,false);
    Image spear2image = new Image(toolFolder + "SpearGrid.png",32,32,true,false);
    Image stick2image = new Image(toolFolder + "StickGrid.png",32,32,true,false);
    Image stone2image = new Image(toolFolder + "StoneGrid.png",32,32,true,false);
    Image torch2image = new Image(toolFolder + "TorchGrid.png",32,32,true,false);
    private void loadItemImages(){
        Item currentitem = game.getAgent().getEquippedItem();
        if (currentitem instanceof Hand) {
            itemGrid.add(new ImageView(fist2image), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Stone) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stone2image), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Spear) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spear2image), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Torch) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torch2image), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Berry) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berry2image), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Meat) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meat2image), 6, 0);
            itemGrid.add(new ImageView(stickimage), 7, 0);
        }
        else if (currentitem instanceof Stick) {
            itemGrid.add(new ImageView(fistimage), 1, 0);
            itemGrid.add(new ImageView(stoneimage), 2, 0);
            itemGrid.add(new ImageView(spearimage), 3, 0);
            itemGrid.add(new ImageView(torchimage), 4, 0);
            itemGrid.add(new ImageView(berryimage), 5, 0);
            itemGrid.add(new ImageView(meatimage), 6, 0);
            itemGrid.add(new ImageView(stick2image), 7, 0);
        }
    }

    public void loadScene(){
        mainScene = mainGrid.getScene();
        mainScene = itemGrid.getScene();
        mainScene.setOnKeyPressed((e) -> handleKeyPress(e));
        mainScene.setOnKeyReleased((e) -> handleKeyRelease(e));
    }



}
