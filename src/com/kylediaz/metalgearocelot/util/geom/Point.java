package com.kylediaz.metalgearocelot.util.geom;

import com.kylediaz.metalgearocelot.camera.Focusable;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double implements Focusable {

    public Point() {
        super();
    }
    public Point(double x, double y) {
        super(x, y);
    }

    @Override
    public Point2D.Double getFocusPoint() {
        return this;
    }
}
