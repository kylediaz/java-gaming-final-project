package com.kylediaz.metalgearocelot.camera;

import com.kylediaz.metalgearocelot.entity.Entity;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera extends Entity {

    private Focusable focus;
    private Dimension dimension;

    public Camera(Focusable focus, Dimension dimension) {
        this.focus = focus;
        this.dimension = (Dimension) dimension.clone();
    }
    public Camera(Focusable focus, int width, int height) {
        this.focus = focus;
        this.dimension = new Dimension(width, height);
    }

    public AffineTransform transformToFocus() {
        AffineTransform output = new AffineTransform();
        double dx = -focus.getFocusPoint().x + (dimension.width / 2);
        double dy = -focus.getFocusPoint().y + (dimension.height / 2);
        output.translate((int) Math.round(dx), (int) Math.round(dy));
        return output;
    }

    /**
     * instantaneously changes the focus of the camera
     * @param focus new focus
     */
    public void setFocus(Focusable focus) {
        this.focus = focus;
    }

    private boolean panning = false;
    private Point target;
    private double speed;

    /**
     * pans to the new focus
     * @param focus new focus
     * @param speed units per second the camera moves at to the focus
     */
    public void setFocus(Focusable focus, double speed) {

    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
