package com.kylediaz.metalgearocelot.entity;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Entity {

    private Set<Effect> effects = Collections.synchronizedSet(new HashSet<>());

    /**
     * @param g2d Graphics2D var of the game it is in
     */
    public void draw(Graphics2D g2d) {

    }

    public void tick(double deltaTime) {
        Set<Effect> copyOfEffects = Set.copyOf(effects);
        copyOfEffects.forEach(e -> e.tick(deltaTime));
    }

    public Collection<Effect> effects() {
        return effects;
    }

    public abstract class Effect {

        public Effect() {
            effects.add(this);
        }

        protected void tick(double deltaTime) {
            periodic(deltaTime);
            if (isFinished()) {
                end();
                effects.remove(this);
            }
        }

        public abstract void periodic(double deltaTime);

        private boolean interrupted;
        public void interrupt() {
            interrupted = true;
        }
        public boolean isInterrupted() {
            return interrupted;
        }

        public boolean isFinished() {
            return isInterrupted();
        }

        protected abstract void end();

    }

    /**
     * A type of <code>Effect</code> that only executes the code in the constructor, and then ends
     */
    public abstract class InstantEffect extends Effect {
        @Override
        public final void periodic(double deltaTime) {}

        /**
         * @return always true
         */
        @Override
        public final boolean isFinished() {
            return true;
        }
        @Override
        public final void end() {}
    }
}
