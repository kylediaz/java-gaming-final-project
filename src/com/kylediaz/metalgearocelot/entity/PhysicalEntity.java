package com.kylediaz.metalgearocelot.entity;

import com.kylediaz.metalgearocelot.camera.Focusable;
import com.kylediaz.metalgearocelot.util.Vector;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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

    public void setX(double x) {
        bounds.x = x;
    }
    public void setY(double y) {
        bounds.y = y;
    }

    public void setWidth(double width) {
        bounds.width = width;
    }
    public void setHeight(double height) {
        bounds.height = height;
    }

    public Vector getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

}
