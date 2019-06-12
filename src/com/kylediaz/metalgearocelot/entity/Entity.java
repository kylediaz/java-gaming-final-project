package com.kylediaz.metalgearocelot.entity;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Entity {

    private Set<Effect> effects = new HashSet<>();

    /**
     * @param g2d Graphics2D var of the game it is in
     */
    public void draw(Graphics2D g2d) {

    }

    public void tick(double deltaTime) {
        effects.forEach(e -> e.tick(deltaTime));
    }

    public Set<Effect> effects() {
        return effects;
    }

    public abstract class Effect {

        public abstract void tick(double deltaTime);

    }
}
