package com.kylediaz.metalgearocelot.util.geom;

import com.kylediaz.metalgearocelot.camera.Focusable;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D.Double implements Focusable {
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    @Override
    public Point2D.Double getFocusPoint() {
        return new Point(x + (width / 2), y + (height / 2));
    }
}
