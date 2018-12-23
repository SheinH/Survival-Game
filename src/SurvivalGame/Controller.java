package SurvivalGame;

import SurvivalGame.GameLogic.*;
import SurvivalGame.GameLogic.FieldObjects.*;
import SurvivalGame.GameLogic.Items.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;
    public int WIDTH;
    public int HEIGHT;
    @FXML
    private GridPane mainGrid;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane itemGrid;
    @FXML
    private Button pauseButton;
    @FXML
    private Button pickupButton;
    @FXML
    private ToggleButton dropButton;
    private Scene mainScene;
    private SurvivalGame game;
    private HashMap<Class<? extends FieldObject>,Image> objectImages;
    private HashMap<Terrain,Image> terrainImages;
    private HashMap<Class<? extends Item>,Image> itemImages;
    private List<ImageView> imageViewList;
    private HashMap<FieldObject, StackPane> objectStackPaneHashMap;
    private HashMap<Tile, StackPane> tileStackPaneHashMap;
    private Image itemBorderImage;
    private Image chestImage;
    private HashMap<Integer, Image> targetImages;
    private ItemGridPane itembar;

    public Controller(SurvivalGame g) {
        game = g;
        g.setUpdateGui(() -> updateTileGrid());
        terrainImages = new HashMap<>();
        objectImages = new HashMap<>();
        imageViewList = new ArrayList<>();
        objectStackPaneHashMap = new HashMap<>();
        itemImages = new HashMap<>();
        targetImages = new HashMap<>();
        tileStackPaneHashMap = new HashMap<>();
        loadImages();
        chestImage = new Image("file:res" + File.separator + "Terrain_Pictures" + File.separator + "Chest.png",32,32,true,false);
    }

    @FXML
    private void initialize(){
        loadTerrainImages();
        loadObjectImages();
        loadItemImages();
        updateTileGrid();
        addUpdatersToTiles();
        initializeItemBar();
        //game.getAgent().addObserver(() -> Platform.runLater(() ->
        //        itembar.showList(game.getAgent().getItemsList())));
        game.getAgent().getItemsList().addListener(il -> Platform.runLater(() ->
                itembar.showList(il)));
        game.setUpdateGui(() -> updateTileGrid());
        game.getPausedProperty().addListener((o,oldV,newV) ->{
            if(newV)
                pauseButton.setText("Resume");
            else
                pauseButton.setText("Pause");
        });
        pauseButton.setOnMouseClicked((e) -> {
            togglePause();
        });
        pickupButton.setOnMouseClicked((e) -> {
            game.getGameLock().lock();
            Agent agent = game.getAgent();
            Tile tile = agent.getTile();
            ItemsList tileItems = tile.getItemsList();
            ItemsList agentItems = agent.getItemsList();
            for(Item i : tileItems){
                agentItems.add(i);
            }
            tileItems.clear();
            game.getGameLock().unlock();
        });
        ItemsList items = new ItemsList();
        Meat meat = new Meat();
        meat.setQuantity(10);
        items.add(meat);
        game.getField().getTile(new Point(0,1)).getItemsList().add(meat);
    }

    public void initializeItemBar(){
        itembar = new ItemGridPane(itemImages);
        itembar.setGridPane(itemGrid);
        itembar.setOnClickConsumer(i -> {
            if(dropButton.isSelected()){
                try {
                    ItemsList list = game.getAgent().getItemsList();
                    Item item = list.get(i);
                    Item newItem = item.getClass().getConstructor().newInstance();
                    newItem.setQuantity(1);
                    item.setQuantity(item.getQuantity() - 1);
                    Tile tile = game.getAgent().getTile();
                    tile.getItemsList().add(newItem);
                    if(item.getQuantity() == 0)
                        game.getAgent().getItemsList().remove(item);
                }
                catch(Exception neverhappening){}
            }
        });
        //
        itembar.showList(game.getAgent().getItemsList());
        game.getAgent().equippedItemIndexProperty().addListener((o,old,neww) -> itembar.setHighlightIndex((int)neww));

        /*
        StackPane sp = new StackPane();
        sp.setPrefSize(50,50);
        ImageView Border = new ImageView(itemBorderImage);
        sp.getChildren().add(Border);
        sp.getChildren().add(new ImageView(itemImages.get(Spear.class)));
        Label label = new Label("50");
        label.setPadding(new Insets(2,2,2,2));
        label.setTextFill(Color.BLACK);
        label.setStyle("-fx-font-weight:bold; -fx-font-size:12; -fx-stroke:blue; -fx-stroke-width:10;");
        sp.getChildren().add(label);
        StackPane.setAlignment(label,Pos.BOTTOM_RIGHT);
        itemGrid.add(sp,0,0);
        */
    }

    public void updateItemBar(){
        updateItemGrid(itemGrid, game.getAgent().getItemsList());
    }

    public void updateItemGrid(GridPane grid, ItemsList list){
        ItemsList items = game.getAgent().getItemsList();
        for(Item i : items){
            Image image = itemImages.get(i.getClass());
            ImageView imageView = new ImageView(image);
        }
    }

    public void updateItemGrid(GridPane grid, ItemsList list, int selectedItem){

    }

    private void togglePause(){
        if(game.isPaused()) {
            game.unPause();
        }
        else {
            game.pause();
        }
    }

    private void handleKeyPress(KeyEvent key) {
        if(key.getCode() == KeyCode.SPACE)
            togglePause();
        game.getGameLock().lock();
        switch(key.getCode())
        {
            case W:
                game.getAgent().setDirection(Direction.UP);
                game.getAgent().clearPath();
                break;
            case S:
                game.getAgent().setDirection(Direction.DOWN);
                game.getAgent().clearPath();
                break;
            case A:
                game.getAgent().setDirection(Direction.LEFT);
                game.getAgent().clearPath();
                break;
            case D:
                game.getAgent().setDirection(Direction.RIGHT);
                game.getAgent().clearPath();
                break;
            case E:
                game.getAgent().offsetItemIndex(1);
                break;
            case Q:
                game.getAgent().offsetItemIndex(-1);
                break;
            case T:
                if(game.isPaused())
                    game.tick();
                break;
            case SHIFT:
                dropButton.selectedProperty().set(true);
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
                break;
            case SHIFT:
                dropButton.selectedProperty().set(false);
                break;
        }

    }

    private void loadImages(){
        String terrainFolder = "file:res" + File.separator + "Terrain_Pictures" + File.separator;
        Image grassimage = new Image(terrainFolder + "Grass64.png",32,32,true,false);
        Image desertimage = new Image(terrainFolder + "desert.png",32,32,true,false);
        Image waterimage = new Image(terrainFolder + "water.png",32,32,true,false);
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
        Image horseImage = new Image(objectsFolder + "Horse64.png",32,32,true,false);
        objectImages.put(Bear.class, bearimage);
        objectImages.put(Tree.class, treeimage);
        objectImages.put(Rabbit.class, rabbitimage);
        objectImages.put(Bush.class, bushimage);
        objectImages.put(Fish.class, fishimage);
        objectImages.put(Crocodile.class, crocimage);
        objectImages.put(Lion.class, lionimage);
        objectImages.put(Rock.class, rockimage);
        objectImages.put(Agent.class, agentImage);
        objectImages.put(Horse.class, horseImage);
        Image targetimage = new Image(objectsFolder + "Target.png",32,32,true,false);
        targetImages.put(0, targetimage);
        loadItemImages();
    }

    public void addUpdatersToTiles(){
        for(Tile t : game.getField()){
            t.addListener((tile) -> {
                if(tile.hasItem())
                    addChest(tile);
                else
                    removeChest(tile);
            });
        }
    }

    public void requestFocus(){
        mainGrid.requestFocus();
    }

    public void updateTileGrid(){
        for(FieldObject obj : game.getField().getFieldObjects()){
            GridPane.setConstraints(
                    objectStackPaneHashMap.get(obj),
                    obj.getPoint().getX(),
                    obj.getPoint().getY());
        }
        if(game.getAgent().getTile().hasItem()){
            pickupButton.setDisable(false);
        }
        else
            pickupButton.setDisable(true);
    }

    public void addChest(Tile t){
        StackPane sp = tileStackPaneHashMap.get(t);
        if(sp.getChildren().size() == 2)
            return;
        else
            sp.getChildren().add(new ImageView(chestImage));
    }

    public boolean hasChest(Tile t){
        return tileStackPaneHashMap.get(t).getChildren().size() > 1;
    }

    public void removeChest(Tile t){
        StackPane sp = tileStackPaneHashMap.get(t);
        if(sp.getChildren().size() == 1)
            return;
        else
            sp.getChildren().remove(1);
    }

    private void loadTerrainImages(){
        Field field = game.getField();
        for(int y = 0; y < field.getHeight(); y++){
            for(int x = 0; x < field.getWidth(); x++){
                Tile tile = field.getTile(new Point(y,x));
                Image terrainImage = terrainImages.get(tile.getTerrain());
                ImageView imageView = new ImageView(terrainImage);
                StackPane stackpane = new StackPane();
                stackpane.getChildren().add(imageView);
                tileStackPaneHashMap.put(tile,stackpane);
                mainGrid.add(stackpane,x,y);
                stackpane.setOnMouseClicked(e ->{
                    game.getGameLock().lock();
                    game.getAgent().setDestination(tile.getPoint());
                    game.getGameLock().unlock();
                });
            }
        }
    }

    private void loadObjectImages(){
        List<FieldObject> objects = game.getField().getFieldObjects();
        for(FieldObject o : objects){
            StackPane stackPane = new StackPane();
            ImageView imageView = new ImageView(objectImages.get(o.getClass()));
            Text healthText = new Text();
            if(o instanceof MovingFieldObject){
                MovingFieldObject moveObj = (MovingFieldObject) o;
                moveObj.addListener((x) -> {
                    healthText.setText(String.valueOf(moveObj.getHealth()));
                });
                healthText.setFont(Font.font("Tahoma", FontWeight.BOLD, 10));
                healthText.setFill(Color.RED);
                mainGrid.add(healthText, o.getPoint().getX(), o.getPoint().getY());
            }
            stackPane.getChildren().add(imageView);
            stackPane.getChildren().add(healthText);
            StackPane.setAlignment(healthText, Pos.BOTTOM_RIGHT);
            mainGrid.add(stackPane,o.getPoint().getX(),o.getPoint().getY());
            objectStackPaneHashMap.put(o,stackPane);
        }

    }

    public void setVisionGrid() {
        int rows = game.getAgent().getPoint().getY()+6;
        int columns = game.getAgent().getPoint().getX()+6;
        WIDTH = columns * TILE_WIDTH;
        HEIGHT = rows * TILE_HEIGHT;

        clipChildren(mainGrid);
        mainGrid.setMinSize(WIDTH, HEIGHT);
        mainGrid.setPrefSize(WIDTH, HEIGHT);
        mainGrid.setMaxSize(WIDTH, HEIGHT);

    }

    public void clipChildren(Region region) {
        Rectangle clipPane = new Rectangle();
        region.setClip(clipPane);

        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            clipPane.setWidth(newValue.getWidth());
            clipPane.setHeight(newValue.getHeight());
        });
    }


    //Reaplces Objects that can be attack with a an indicator to show that it's in range.
    private void showTargetImage() {
        Field field = game.getField();
        int range = game.getAgent().getEquippedTool().getRange();
        int x = game.getAgent().getPoint().getX();
        int y = game.getAgent().getPoint().getY();
        if (x - range < 0 || y - range < 0) {
            for (int i = 0; i < x + range; i++) {
                for (int j = 0; j < y + range; j++) {
                    Tile tile = field.getTile(new Point(i, j));
                    if (tile.hasObject() && !(tile.getObjects() instanceof Agent)) {
                        ImageView imageView = new ImageView(targetImages.get(0));
                        mainGrid.add(imageView, i, j);
                    }
                }
            }
        } else {
            for (int i = x - range; i < x + range; i++) {
                for (int j = y - range; j < y + range; j++) {
                    Tile tile = field.getTile(new Point(i, j));
                    if (tile.hasObject() && !(tile.getObjects() instanceof Agent)) {
                        ImageView imageView = new ImageView(targetImages.get(0));
                        mainGrid.add(imageView, i, j);
                    }
                }
            }
        }
    }

    private void loadItemImages(){

        String toolFolder = "file:res" + File.separator + "New_Tool_Pictures" + File.separator;
        Image berryimage = new Image(toolFolder + "berry.png",32,32,true,true);
        Image fistimage = new Image(toolFolder + "fist.png",32,32,true,true);
        Image meatimage = new Image(toolFolder + "meat.png",32,32,true,true);
        Image spearimage = new Image(toolFolder + "spear.png",32,32,true,true);
        Image stickimage = new Image(toolFolder + "stick.png",32,32,true,true);
        Image stoneimage = new Image(toolFolder + "stone.png",32,32,true,true);
        Image torchimage = new Image(toolFolder + "torch.png",32,32,true,true);

        itemImages.put(Berry.class,berryimage);
        itemImages.put(Hand.class,fistimage);
        itemImages.put(Meat.class,meatimage);
        itemImages.put(Spear.class,spearimage);
        itemImages.put(Stick.class,stickimage);
        itemImages.put(Stone.class,stoneimage);
        itemImages.put(Torch.class,torchimage);

        itemBorderImage = new Image("file:res" + File.separator + "New_Tool_Pictures" + File.separator + "itemborder.png",50,50,true,false);
    }

    public void loadScene(){
        mainScene = mainGrid.getScene();
        mainScene = itemGrid.getScene();
        mainScene.setOnKeyPressed((e) -> handleKeyPress(e));
        mainScene.setOnKeyReleased((e) -> handleKeyRelease(e));
    }



}
