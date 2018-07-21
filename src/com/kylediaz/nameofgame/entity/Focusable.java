package com.kylediaz.nameofgame.entity;

import java.awt.Point;


/**
 * A Focusable needs to have a Point where the Camera can center itself on
 * @author Kyle Diaz
 */
public interface Focusable {
    
    public Point.Double getFocalPoint();
    
}
