package com.kylediaz.nameofgame.input;

import java.awt.Point;

/**
 * I made this class so all of the information about the mouse can be wrapped
 * into one nice package for the MouseHandler
 * @author 900000779
 */
public class Mouse {
    
    public boolean leftMousePressed;
    public boolean rightMousePressed;
    public Point mouseLocation;
    
    public Mouse(boolean leftMousePressed, boolean rightMousePressed, Point mouseLocation) {
        this.leftMousePressed = leftMousePressed;
        this.rightMousePressed = rightMousePressed;
        this.mouseLocation = mouseLocation;
    }
    public boolean leftMousePressed() {
        return leftMousePressed;
    }
    public boolean rightMousePressed() {
        return rightMousePressed;
    }
    public Point getMouseLocation() {
        return mouseLocation;
    }
}
