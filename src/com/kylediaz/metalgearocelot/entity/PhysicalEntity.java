package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.camera.Focusable;
import com.kylediaz.metalgearocelot.entity.animation.Animation;
import com.kylediaz.metalgearocelot.util.Vector;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * An entity that physically occupies space in the game
 */
public class PhysicalEntity extends Entity implements Focusable {

    private Rectangle.Double bounds;
    private Vector velocity = Vector.ZERO;

    /**
     * A constructor so the client doesn't have to create a <code>Rectangle</code> themself
     * @param x the abscissa of the upper left corner <code>Point</code>
     * @param y the ordinate of the uppder left corner <code>Point</code>
     * @param width the width going right of the <code>Point</code>
     * @param height the height going down from the <code>Point</code>
     */
    public PhysicalEntity(double x, double y, double width, double height) {
        this(new Rectangle2D.Double(x, y, width, height));
    }
    public PhysicalEntity(Rectangle.Double bounds) {
        this.bounds = bounds;
    }

    @Override
    public void tick(double deltaTime) {
        super.tick(deltaTime);
        if (currentEvent == null)
            currentEvent = defaultEvent;
        translate(velocity.getXComponent() * deltaTime, velocity.getYComponent() * deltaTime);
    }

    public Rectangle.Double getBounds() {
        return bounds;
    }
    public boolean collidesWith(PhysicalEntity other) {
        return new Rectangle2D.Double(bounds.x + velocity.getXComponent(), bounds.y + velocity.getYComponent(), bounds.width, bounds.height)
                .intersects(other.bounds);
    }

    public void translate(double dx, double dy) {
        bounds.x += dx;
        bounds.y += dy;
    }
    public void translateX(double dx) {
        translate(dx, 0);
    }
    public void translateY(double dy) {
        translate(0, dy);
    }

    public Vector getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public Point2D.Double getFocusPoint() {
        return new Point2D.Double(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    private Event currentEvent;
    private Event defaultEvent;

    /**
     * Unlike <code>Effect</code>s, a <code>PhysicalEntity</code> can only have one active <code>Event</code> at a
     * time because the active <code>Event</code> defines how the <code>Entity</code> is drawn.
     * @param <A> the type of animation it uses
     */
    public abstract class Event<A extends Animation> extends Effect {

        protected void tick(double deltaTime) {
            super.tick(deltaTime);
            if (getAnimation() != null) {
                getAnimation().cycle();
            }
        }

        @Override
        public boolean isFinished() {
            return (getAnimation() != null && getAnimation().isFinished()) || super.isFinished();
        }
        @Override
        protected void end() {
            currentEvent = defaultEvent;
        }

        public abstract A getAnimation();
    }

    public void setCurrentEvent(Event e) {
        currentEvent = e;
    }
    public Event getCurrentEvent() {
        return currentEvent;
    }
    public void setDefaultEvent(Event e) {
        defaultEvent = e;
    }
    public Event getDefaultEvent() {
        return defaultEvent;
    }

}
