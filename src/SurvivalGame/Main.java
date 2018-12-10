package SurvivalGame;

import SurvivalGame.GameLogic.SurvivalGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Button;

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
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest( t -> {
            Platform.exit();
            System.exit(0);
        });
        c.requestFocus();
        c.setText(game.getField().toString());
        game.update();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
