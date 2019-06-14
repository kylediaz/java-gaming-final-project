package com.kylediaz.metalgearocelot.input.device;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

public class Mouse implements MouseListener, MouseMotionListener {

    private Map<Integer, Boolean> pressedButtons = new HashMap<>();
    private Point position = new Point();

    public Mouse() {

    }
    public Mouse(Container c) {
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    public boolean isPressed(int button) {
        return pressedButtons.containsKey(button) && pressedButtons.get(button);
    }
    public Point getPosition() {
        return position;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedButtons.put(e.getButton(), true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedButtons.put(e.getButton(), false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = e.getPoint();
    }

    // Unused inherited methods
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
