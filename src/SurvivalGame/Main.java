package SurvivalGame;

import SurvivalGame.GameLogic.SurvivalGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller c;
    private SurvivalGame game;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        c = new Controller();
        loader.setController(c);
        Parent root = loader.load();
        primaryStage.setTitle("Survival Game");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest( t -> {
            Platform.exit();
            System.exit(0);
        });
        c.setText("Sample Text");
        c.requestFocus();
        game = new SurvivalGame();
        game.readFile();
        c.setText(game.getField().toString());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
