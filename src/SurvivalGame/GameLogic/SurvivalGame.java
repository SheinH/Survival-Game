package SurvivalGame.GameLogic;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;

public class SurvivalGame {
    Field field;
    long tickCount;
    ScheduledExecutorService gameThread;
    ReentrantLock gameLock;
    boolean paused;
    ReentrantLock gamePauseLock;
    Runnable updateGui;
    Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public SurvivalGame(){
        gameLock = new ReentrantLock();
        gamePauseLock = new ReentrantLock();
        gameThread = Executors.newSingleThreadScheduledExecutor();
        paused = true;
        gamePauseLock.lock();
        gameThread.scheduleAtFixedRate(() -> update(), 0, 1 , TimeUnit.SECONDS);
    }

    public void update(){
        gameLock.lock();
        gamePauseLock.lock();
        /*tickCount++;
        System.out.println("UPDATE");
        agent.update();
        if(updateGui != null)
            updateGui.run();*/
        updateGui.run();
        tickCount++;
        gamePauseLock.unlock();
        gameLock.unlock();
    }

    public long getTickCount() {
        return tickCount;
    }

    public void setUpdateGui(Runnable updateGui) {
        this.updateGui = updateGui;
    }

    public void pause(){
        if(!paused){
            gamePauseLock.lock();
            paused = true;
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
        if(paused){
            gamePauseLock.unlock();
            paused = false;
        }
    }
}
