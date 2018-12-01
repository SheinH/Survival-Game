package SurvivalGame.GameLogic;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Executors;

public class Game {
    Field field;
    ScheduledExecutorService gameThread;
    ReentrantLock gameLock;
    boolean paused;
    ReentrantLock gamePauseLock;
    public Game(){
        gameLock = new ReentrantLock();
        gamePauseLock = new ReentrantLock();
        gameThread = Executors.newSingleThreadScheduledExecutor();
        gameThread.scheduleAtFixedRate(() -> update(), 0, 33333, TimeUnit.MILLISECONDS);
    }

    public void update(){
        gameLock.lock();
        gamePauseLock.lock();
        field.getFieldObjects().forEach((obj) -> obj.update());
        gameLock.unlock();
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
