package com.kylediaz.metalgearocelot.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Keyboard implements KeyListener, Iterable<Integer> {

    private Set<Integer> pressedKeys = new HashSet<>();

    public Keyboard() {

    }
    public Keyboard(Component c) {
        c.addKeyListener(this);
    }

    public boolean isPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public Iterator<Integer> iterator() {
        return pressedKeys.iterator();
    }

    // Unused inherited method
    @Override
    public void keyTyped(KeyEvent e) { }

}
