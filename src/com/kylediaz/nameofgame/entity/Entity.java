package com.kylediaz.nameofgame.entity;

import com.kylediaz.nameofgame.util.Camera;
import com.kylediaz.nameofgame.Game;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.logging.Level;

/**
 * 
 * @author kyled
 */
public abstract class Entity implements Focusable, Serializable {
    
    public final String NAME;
    public final Game game;
    
    public Rectangle2D.Double bounds; // Dimension of "idle" sprite
    
    public boolean toBeRemoved = false;
    
    public final HashMap<String, Event> eventDirectory = new HashMap<>();
    private Event currentlyActiveEvent;
    private boolean currentlyInEvent = false;

    public Entity(Game game, File directory) {
        this.game = game;
        this.NAME = directory.getName();
        Game.LOGGER.log(Level.INFO, "Entity " + NAME + " is using files " + directory.listFiles());
        for (File file : directory.listFiles()) {
            eventDirectory.put(file.getName(), new Event(file));
        }
    }

    /**
     * This method will be overridden, making the body of the method the logic 
     * of the entity that will be computed every refresh   
     */
    public abstract void tick();
    
    protected BufferedImage frame;
    
    public abstract void render(Graphics2D g2d, Camera camera);
    
    private class Event {

        public final String ID;
        private boolean interuptable;
        public final float startTime;
        
        private ArrayList<BufferedImage> frames;

        public Event(File directory) {
            this.ID = directory.getName().replace("event_", "");
            this.startTime = System.nanoTime();
            
            
            eventDirectory.put(ID, this);
        }
        
        public BufferedImage getCurrentFrame() {
            return frames.get(((int) (System.nanoTime() - startTime) / frames.size()) % frames.size());
        }
            
        private void end() {
            currentlyInEvent = false;
        }
        
        public final boolean isInteruptable() {
            return interuptable;
        }

    }
    
    protected synchronized void startEvent(Event event) {
        currentlyActiveEvent = event;
        currentlyInEvent = true;
    }
    public synchronized boolean isInAnEvent() {
        return currentlyInEvent;
    }
    protected synchronized Event getCurrentlyActiveEvent() throws Exception {
        if (!this.isInAnEvent())
            return currentlyActiveEvent;
        else
            throw new Exception(NAME + " is not in an event");
    }
    
    @Override
    public Point.Double getFocalPoint() {
        return new Point.Double(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }
    
}
