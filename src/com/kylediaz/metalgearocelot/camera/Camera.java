package com.kylediaz.metalgearocelot.camera;

import com.kylediaz.metalgearocelot.entity.Entity;

import java.awt.*;

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

    public void setFocus(Focusable focus) {
        this.focus = focus;
    }
    private boolean panning = false;
    private Point target;
    private double speed;
    public void setFocus(Focusable focus, double speed) {

    }


    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
