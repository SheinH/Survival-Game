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

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        c.loadScene();
        primaryStage.show();
        primaryStage.setOnCloseRequest( t -> {
            Platform.exit();
            System.exit(0);
        });
        c.requestFocus();
        game.getAgent().setDirection(Direction.RIGHT);
        game.unPause();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
