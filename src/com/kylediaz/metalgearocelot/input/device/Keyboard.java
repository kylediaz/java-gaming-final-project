package com.kylediaz.metalgearocelot.input.device;

import com.kylediaz.metalgearocelot.input.InputButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Keyboard implements KeyListener, Iterable<Keyboard.Button> {

    private Set<Keyboard.Button> pressedKeys = new TreeSet<>();

    public Keyboard() {

    }
    public Keyboard(Component c) {
        c.addKeyListener(this);
    }

    public boolean isPressed(int keyCode) {
        return pressedKeys.contains(new Button(keyCode));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(new Button(e.getKeyCode()));
    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(new Button(e.getKeyCode()));
    }

    @Override
    public Iterator<Keyboard.Button> iterator() {
        return pressedKeys.iterator();
    }

    public class Button implements InputButton, Comparable<Button> {

        private int keyCode;

        public Button(int keyCode) {
            this.keyCode = keyCode;
        }

        @Override
        public boolean get() {
            return isPressed(keyCode);
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Button && (this.compareTo((Button) o) == 0);
        }
        @Override
        public int compareTo(Button o) {
            return this.keyCode - o.keyCode;
        }
    }

    // Unused inherited method
    @Override
    public void keyTyped(KeyEvent e) { }

}
