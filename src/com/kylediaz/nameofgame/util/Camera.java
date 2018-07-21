package com.kylediaz.nameofgame.util;

import com.kylediaz.nameofgame.entity.Focusable;


/**
 * A data class containing information on the part of the map that will be rendered
 * @author kyled
 */
public class Camera {
    
    public Focusable focus;
    public Dimension dimension;
    
    public Camera(Focusable focus, Dimension dimension) {
        this.focus = focus;
        this.dimension = dimension;
    }
    
}
