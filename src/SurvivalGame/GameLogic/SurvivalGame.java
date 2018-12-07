package SurvivalGame.GameLogic;

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
        System.out.println("TICK");
        tickCount++;
        //field.getFieldObjects().forEach((obj) -> obj.update());
        gamePauseLock.unlock();
        gameLock.unlock();
        if(updateGui != null)
            updateGui.run();
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

    public void unPause(){
        if(paused){
            gamePauseLock.unlock();
            paused = false;
        }
    }
}
