package com.kylediaz.nameofgame.input;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Keeps track of the mouse position and whether the mouse buttons are pressed
 * @author kyled
 */
public class MouseHandler implements MouseListener, MouseMotionListener {
    
    private Mouse mouse;
    
    public MouseHandler() {
        // Prevents NullPointerExceptions
        mouse = new Mouse(false, false, new Point(0, 0));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            mouse.leftMousePressed = true;
        else
            mouse.rightMousePressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            mouse.leftMousePressed = false;
        else
            mouse.rightMousePressed = false;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouse.mouseLocation = new Point(e.getX(), e.getY());
    }
    
    public Mouse getMouse() {
        return mouse;
    }
    
    // Below this line are unused inherited methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
