package SurvivalGame;

import SurvivalGame.GameLogic.SurvivalGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Button;

public class Main extends Application {

    private Controller c;
    private SurvivalGame game;
    Button button;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameLayout.fxml"));
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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
