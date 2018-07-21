package com.kylediaz.nameofgame.entity;

import com.kylediaz.nameofgame.Game;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;

/**
 * A LogicController is a thread where all of the computing for the game happens.
 * Because the logic is on a different thread than the paint() method, the framerate
 * won't be tied to the tickrate of the game, allowing me to change the tickrate
 * (ie slow down, speed up the game time) without changing the framerate. It also
 * makes the makes the program more efficient because the paint method does not
 * have to do logic AND paint at the same time. It also makes more sense pragmatically
 * because the name of paint() implies that all it should do is paint the scene, not
 * compute the logic behind the game.
 * @author 900000779
 */

public abstract class LogicController extends Thread {
    
    public final long TICKRATE;
    public double speed;
    
    public LogicController(long TICKRATE) {
        super("Logic Controller");
        this.TICKRATE = TICKRATE;
    }
    
    /**
     * This method will be called every TICKRATE seconds from the run() method.
     */
    public abstract void tick();
    
    /**
      * This method is run on a separate thread, calling tick() every TICKRATE
      * seconds.
     */
    @Override
    public final void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            try {
                tick();
            } catch (ConcurrentModificationException e) {
                Game.LOGGER.log(Level.SEVERE, e.getMessage() + " " + e.getCause());
            }
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = TICKRATE - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.err.println("dammn daniel");
            }
            beforeTime = System.currentTimeMillis();

        }
    }
    
}