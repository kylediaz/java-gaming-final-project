package com.kylediaz.nameofgame;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kyled
 */
public class Arena extends Map {
    
    private ArrayList<Rectangle> collisionAreas = new ArrayList<>();
    
    public Arena(File filePath) throws IOException {
        super(filePath);
        collisionAreas.add(new Rectangle(0, 0, 15, 255));
        collisionAreas.add(new Rectangle(0, 0, 255, 15));
        collisionAreas.add(new Rectangle(0, 239, 255, 15));
        collisionAreas.add(new Rectangle(240, 0, 15, 255));
        
        collisionAreas.add(new Rectangle(80, 47, 31, 64));
        collisionAreas.add(new Rectangle(47, 79, 64, 32));
        collisionAreas.add(new Rectangle(144, 47, 31, 64));
        collisionAreas.add(new Rectangle(144, 79, 63, 32));
        
        collisionAreas.add(new Rectangle(80, 144, 31, 64));
        collisionAreas.add(new Rectangle(48, 143, 64, 31));
        collisionAreas.add(new Rectangle(144, 143, 31, 64));
        collisionAreas.add(new Rectangle(144, 143, 64, 32));
    }
    
    public boolean isOccupiedSpot(Rectangle2D.Double bounds) {
        for (Rectangle area : collisionAreas) {
            if (bounds.intersects(area)) return true;
        }
        return false;
    }
    
}
