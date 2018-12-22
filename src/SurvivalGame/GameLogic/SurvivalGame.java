package SurvivalGame.GameLogic;

import SurvivalGame.GameLogic.Items.Berry;
import SurvivalGame.GameLogic.Items.Item;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class SurvivalGame {
    Field field;
    long tickCount;
    ScheduledExecutorService gameThread;
    ScheduledFuture currentTask;
    ReentrantLock gameLock;
    BooleanProperty pausedProperty;
    ReentrantLock gamePauseLock;
    Runnable updateGui;
    Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ReentrantLock getGameLock() {
        return gameLock;
    }

    public boolean isPaused() {
        return pausedProperty.get();
    }

    public BooleanProperty getPausedProperty() {
        return pausedProperty;
    }

    public SurvivalGame(){
        gameLock = new ReentrantLock();
        gamePauseLock = new ReentrantLock();
        gameThread = Executors.newSingleThreadScheduledExecutor();
        pausedProperty = new SimpleBooleanProperty(true);
        gamePauseLock.lock();
    }

    public void update(){
        gameLock.lock();
        try {
            gamePauseLock.lock();
            try {
                tick();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally {
                gamePauseLock.unlock();
            }
        }
        finally {
            gameLock.unlock();
        }
    }


    public void tick(){
        field.getFieldObjects().forEach((obj) -> {obj.update(); obj.updateListeners();});
        if (updateGui != null)
            updateGui.run();
        tickCount++;
    }

    public long getTickCount() {
        return tickCount;
    }

    public void setUpdateGui(Runnable updateGui) {
        this.updateGui = updateGui;
    }

    public void pause(){
        if(!pausedProperty.get()){
            gamePauseLock.lock();
            pausedProperty.set(true);
            currentTask.cancel(false);
        }
    }

    public Field getField() {
        return field;
    }

    public void readFile(){
        File file = new File("SurvivalGameLayout.txt");
        try{
            Scanner sc = new Scanner(file);
            field = Loader.loadTiles(sc);
            field.setGame(this);
            Loader.loadObjects(sc, field);
        }
        catch(FileNotFoundException e){
            System.out.println("NOT FOUND");
        }
    }

    public void unPause(){
        if(pausedProperty.get()){
            gamePauseLock.unlock();
            pausedProperty.set(false);
            currentTask =  gameThread.scheduleAtFixedRate(() -> update(), 0, 100 , TimeUnit.MILLISECONDS);
        }
    }
}
