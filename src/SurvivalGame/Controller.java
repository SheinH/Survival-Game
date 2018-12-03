package SurvivalGame;

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

    public void setText(String text) {
        textArea.setText(text);
    }

    private void handleKeyPress(KeyEvent key) {
        switch(key.getCode())
        {
            case W:
                textArea.setText("UP PRESSED");
                break;
            case S:
                textArea.setText("DOWN PRESSED");
                break;
            case A:
                textArea.setText("LEFT PRESSED");
                break;
            case D:
                textArea.setText("RIGHT PRESSED");
                break;
            default:
                textArea.setText("UNRECOGNIZED KEY");
        }

    }

    public void requestFocus(){
        textArea.requestFocus();
    }
    public Controller() {
    }

    @FXML
    private void initialize(){
        textArea.setOnKeyPressed((key) -> handleKeyPress(key));
    }
}
