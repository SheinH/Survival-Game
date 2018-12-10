package SurvivalGame;

import SurvivalGame.GameLogic.FieldObjects.Direction;
import SurvivalGame.GameLogic.SurvivalGame;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private BorderPane mainPane;
    private Scene mainScene;
    private SurvivalGame game;

    public void setText(String text) {
        textArea.setText(text);
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

    public void requestFocus(){
        textArea.requestFocus();
    }
    public Controller(SurvivalGame g) {
        game = g;
        g.setUpdateGui(() -> setText(g.getField().toString()));
    }

    @FXML
    private void initialize(){
        game.getAgent().setDirection(Direction.RIGHT);
    }
}
