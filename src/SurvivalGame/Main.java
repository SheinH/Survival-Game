package SurvivalGame;

import SurvivalGame.GameLogic.*;
import SurvivalGame.GameLogic.FieldObjects.*;
import SurvivalGame.GameLogic.Point;
import SurvivalGame.GameLogic.FieldObjects.Direction;
import SurvivalGame.GameLogic.SurvivalGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {

    private Controller c;
    private SurvivalGame game;
    Button button;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        game = new SurvivalGame();
        game.readFile();
        c = new Controller(game);
        loader.setController(c);
        Parent root = loader.load();
        primaryStage.setTitle("Survival Game");

        GridPane gridpane = new GridPane();
        Image grassimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Grass64.png");
        Image desertimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Desert64.png");
        Image waterimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Water64.png");
        Image bearimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Animal_Pictures/Bear64.png");
        Image treeimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Tree64.png");
        Image rabbitimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Animal_Pictures/Rabbit64.png");
        Image bushimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Bush64.png");
        Image fishimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Animal_Pictures/Fish64.png");
        Image crocimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Animal_Pictures/Croc64.png");
        Image lionimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Animal_Pictures/Lion64.png");
        Image rockimage = new Image("/Users/steve/Desktop/JAVA/Survival-Game/Terrain_Pictures/Rock64.png");

        /*
                    if (c == 'R'){
                        gridpane.add(new ImageView(rabbitimage), y, x);}
                    else if (c == 'T'){
                        gridpane.add(new ImageView(treeimage), y, x);}
                    else if (c == 'B') {
                        gridpane.add(new ImageView(bushimage), y, x);}
                    else if (c == 'C') {
                        gridpane.add(new ImageView(crocimage), y, x);}
                    else if (c == 'L') {
                        gridpane.add(new ImageView(lionimage), y, x);}
                    else if (c == 'F') {
                        gridpane.add(new ImageView(fishimage), y, x);}
                    else if (c == 'r') {
                        gridpane.add(new ImageView(rockimage), y, x);}

        */

        //gridpane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridpane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest( t -> {
            Platform.exit();
            System.exit(0);
        });

        c.setText("Sample Text");
        c.requestFocus();
        game = new SurvivalGame();
        game.readFile();
        Tile [][] grid = game.getField().getTileGrid();
        int h = grid.length;
        int w = grid[0].length;
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++) {
                char c = grid[y][x].getTerrain().getCharacter();
                if (c == 'G'){
                    gridpane.add(new ImageView(grassimage), x, y);}
                else if (c == 'W'){
                    gridpane.add(new ImageView(waterimage), x, y);}
                else if (c == 'D') {
                    gridpane.add(new ImageView(desertimage), x, y);}
            }
        }
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                for (int z = 0; z < grid[y][x].getObjects().size(); z++){
                    FieldObject obj = grid[y][x].getObjects().get(z);
                    if (obj instanceof Rabbit){
                        gridpane.add(new ImageView(rabbitimage), x, y);}
                     else if (obj instanceof Tree){
                        gridpane.add(new ImageView(treeimage), x, y);}
                    else if (obj instanceof Bush) {
                        gridpane.add(new ImageView(bushimage), x, y);}
                    else if (obj instanceof Crocodile) {
                        gridpane.add(new ImageView(crocimage), x, y);}
                    else if (obj instanceof Lion) {
                         gridpane.add(new ImageView(lionimage), x, y);}
                    else if (obj instanceof Fish) {
                        gridpane.add(new ImageView(fishimage), x, y);}
                    else if (obj instanceof Rock) {
                        gridpane.add(new ImageView(rockimage), x, y);}
                    else if (obj instanceof Bear) {
                        gridpane.add(new ImageView(bearimage), x, y);}
                }
            }

        }

        gridpane.setGridLinesVisible(true);
        c.setText(game.getField().toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
