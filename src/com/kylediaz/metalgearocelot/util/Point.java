package com.kylediaz.metalgearocelot.util;

import com.kylediaz.metalgearocelot.camera.Focusable;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double implements Focusable {
    @Override
    public Point2D.Double getFocusPoint() {
        return this;
    }
}
