package com.kylediaz.metalgearocelot.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 */
public class EntityManager extends Thread implements Iterable<Entity> {

    private Collection<Entity> entities = new HashSet<>();

    private final int TICKRATE = 60;
    private final int DELAY = 1000 / TICKRATE;

    private long startTime = System.currentTimeMillis(), deltaTime = 0, beforeTime, sleep;

    public EntityManager() {
        super("Entity Manager");
        this.start();
    }

    @Override
    public void run() {
        beforeTime = System.currentTimeMillis();
        while (!interrupted()) {
            tick();

            deltaTime = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - deltaTime;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                System.err.println(msg);
            }
            beforeTime = System.currentTimeMillis();
        }
    }

    protected void tick() {
        updateList();
        tickEntities();
    }
    protected void tickEntities() {
        for (Entity e : entities)
            e.tick(getDeltaTime());
    }
    protected void updateList() {
        for (Entity e : toBeAdded)
            entities.add(e);
        for (Entity e : toBeRemoved)
            entities.remove(e);
    }

    /**
     * @return time of initialization in milliseconds
     */
    public long getStartTime() {
        return startTime;
    }
    /**
     * @return number of ellapsed ticks
     */
    public long getTotalTicks() {
        return (System.currentTimeMillis() - startTime) / DELAY;
    }
    /**
     * @return time between this tick and previous tick as a fraction of a second
     */
    public double getDeltaTime() {
        return sleep / 1000.0;
    }

    private Collection<Entity> toBeAdded = new LinkedList<>();
    public void add(Entity e) {
        toBeAdded.add(e);
    }

    private Collection<Entity> toBeRemoved = new LinkedList<>();
    public void remove(Entity e) {
        toBeRemoved.add(e);
    }

    @Override
    public Iterator<Entity> iterator() {
        return entities.iterator();
    }
}
